package com.javainuse.service;

import com.javainuse.models.DriveInvitedDonors;
import com.javainuse.models.Drives;
import com.javainuse.models.Notification;
import com.javainuse.models.ProfileInd;
import com.javainuse.repositories.DriveInvitedDonorsRepo;
import com.javainuse.repositories.DrivesRepo;
import com.javainuse.repositories.NotificationRepo;
import com.javainuse.repositories.ProfileIndRepo;
import com.javainuse.requests.CancelDrive;
import com.javainuse.requests.DriveDonorVerification_ReqBody;
import com.javainuse.responses.DriveDonorsList_RespBody;
import com.javainuse.responses.FetchDrivesResponseBody;
import com.javainuse.responses.SuccessResponseBody;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class MyDrivesDAO {

    @Autowired
    DrivesRepo drivesRepo;

    @Autowired
    DriveInvitedDonorsRepo driveInvitedDonorsRepo;

    @Autowired
    NotificationRepo notificationRepo;

    @Autowired
    ProfileIndRepo profileIndRepo;


    @Autowired
    AndroidPushNotificationsService androidPushNotificationsService;

    private final List<String> allBloodGroups = new ArrayList<>(Arrays.asList("B+", "B-", "A+", "A-", "O+", "O-", "AB+", "AB-"));

    public ResponseEntity<SuccessResponseBody> cancelDrive(CancelDrive cancelDrive, String userId){
        try{
            Drives drive = drivesRepo.findByDriveId(cancelDrive.getDriveId());
            if(userId.equals(drive.getUserId())){
                drive.setStatus(false);
                drivesRepo.save(drive);

                //? SENDING NOTIFICATIONS TO ALL INDIVIDUALS WHI HAVE REGISTERED FOR THE DRIVE.

                List<DriveInvitedDonors> donorsList = driveInvitedDonorsRepo.findByDriveId(cancelDrive.getDriveId());
                for ( DriveInvitedDonors driveInvitedDonor : donorsList ){
                    Notification notification = new Notification(driveInvitedDonor.getUserId(), "Drive cancelled", "Drive " + cancelDrive.getDriveId() + " has been cancelled by the organizer.", new Timestamp(System.currentTimeMillis()));
                    notificationRepo.save(notification);
                }
                HttpHeaders responseHeaders = new HttpHeaders();
                responseHeaders.set("success", "true");
                return ResponseEntity.ok().headers(responseHeaders).body(new SuccessResponseBody(true));
            }
            else{
                HttpHeaders responseHeaders = new HttpHeaders();
                responseHeaders.set("error", "You are not authorized to perform this action.");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).headers(responseHeaders).build();
            }
        }
        catch (Exception e){
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("error", "Cannot cancel current drive right now.");
            return ResponseEntity.notFound().headers(responseHeaders).build();
        }
    }

    public ResponseEntity<List<FetchDrivesResponseBody>> fetchMyDrives(String userId){
        try{
            List<Drives> drivesList = drivesRepo.findByUserId(userId);
            List<FetchDrivesResponseBody> responseList = new ArrayList<>();

            for(Drives drive : drivesList ){
                List<Boolean> bloodGroupsInvited = new ArrayList<>(Arrays.asList(drive.getbPos(), drive.getbNeg(), drive.getaPos(), drive.getaNeg(), drive.getoPos(), drive.getoNeg(), drive.getAbPos(), drive.getAbNeg()));
                List<String> bloodGroups = new ArrayList<>();
                for (int i = 0;i < bloodGroupsInvited.size(); i ++){
                    if(bloodGroupsInvited.get(i)){
                        bloodGroups.add(allBloodGroups.get(i));
                    }
                }
                responseList.add(new FetchDrivesResponseBody(drive.getDriveId(), drive.getStartTimestamp(), drive.getEndTimestamp(), drive.getAddress(), drive.getState(), drive.getState(), drive.getPincode(), bloodGroups, drive.getStatus(), drive.getRequestTime()));
            }

            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("success", "true");
            return ResponseEntity.ok().headers(responseHeaders).body(responseList);

        }
        catch(Exception e){
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("error", "Cannot fetch list of drives right now. Please try again later");
            return ResponseEntity.notFound().headers(responseHeaders).build();
        }
    }

    public ResponseEntity<SuccessResponseBody> driveDonorVerify(DriveDonorVerification_ReqBody driveDonorVerification_reqBody, String userId){
        try{
            HttpHeaders responseHeaders = new HttpHeaders();
            String organizerId = drivesRepo.findByDriveId(driveDonorVerification_reqBody.getDriveId()).getUserId();
            if(organizerId.equals(userId)){

                DriveInvitedDonors driveInvitedDonor = driveInvitedDonorsRepo.findByDriveIdAndUserId(driveDonorVerification_reqBody.getDriveId(), driveDonorVerification_reqBody.getUserId());
                driveInvitedDonor.setDonation_status(true);
                driveInvitedDonor.setDonationTimestamp(new Timestamp(System.currentTimeMillis()));
                driveInvitedDonorsRepo.save(driveInvitedDonor);

                //? CHANGING LAST DONATION DATE AND DONOR STATUS AT FRONT END.
                ProfileInd profileInd = profileIndRepo.findByUserId(driveDonorVerification_reqBody.getUserId());
                profileInd.setDonorStatus(2);
                profileInd.setLast_donation_date(new Timestamp(System.currentTimeMillis()));
                profileIndRepo.save(profileInd);


                //FIREBASE NOTIFICATION
                JSONObject body = new JSONObject();
                body.put("to", "/topics/" + driveDonorVerification_reqBody.getUserId());
                body.put("priority", "high");

                JSONObject firebaseNotification = new JSONObject();
                firebaseNotification.put("title", "Donation Verified!");
                firebaseNotification.put("body", "Your donation for drive : " + driveDonorVerification_reqBody.getDriveId() + " has been verified by the organizer.");

                JSONObject data = new JSONObject();


                body.put("notification", firebaseNotification);

                //TODO REMOVE IF NO LONGER NEEDED
                driveDonorVerification_reqBody.getDriveId();
                body.put("data", data);

                HttpEntity request = new HttpEntity<>(body.toString());

                CompletableFuture pushNotification = androidPushNotificationsService.send(request);
                CompletableFuture.allOf(pushNotification).join();


                //? SENDING NOTIFICATION TO THE DONOR.
                Notification notification = new Notification(driveDonorVerification_reqBody.getUserId(), "Donation complete", "Your blood donation has been verified for drive: " + driveDonorVerification_reqBody.getDriveId(), new Timestamp(System.currentTimeMillis()));
                notificationRepo.save(notification);

                responseHeaders.set("success", "true");
                return ResponseEntity.ok().headers(responseHeaders).body(new SuccessResponseBody(true));
            }
            else{
                responseHeaders.set("error", "You are not authorized to perform this action.");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).headers(responseHeaders).build();
            }
        }
        catch (Exception e){
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("error", "Cannot verify donation right now, Please try again later.");
            return ResponseEntity.notFound().headers(responseHeaders).build();
        }
    }

    public ResponseEntity<List<DriveDonorsList_RespBody>> getDriveDonorList(String driveId, String userId){
        try{
            HttpHeaders responseHeaders = new HttpHeaders();
            String organizerId = drivesRepo.findByDriveId(driveId).getUserId();
            if(organizerId.equals(userId)){
                responseHeaders.set("success", "true");

                List<DriveDonorsList_RespBody> responseList = new ArrayList<>();
                List<DriveInvitedDonors> driveInvitedDonorsList = driveInvitedDonorsRepo.findByDriveId(driveId);
                for(DriveInvitedDonors driveInvitedDonors : driveInvitedDonorsList)
                {
//                if(driveInvitedDonors.getAcceptance() == 1){
                    ProfileInd profileInd=profileIndRepo.findByUserId(driveInvitedDonors.getUserId());
                    responseList.add(new DriveDonorsList_RespBody(driveInvitedDonors.getUserId(), profileInd.getName(),profileInd.getBloodGroup(),driveInvitedDonors.isDonation_status(), driveInvitedDonors.getAcceptance()));

//                }
                }

                return ResponseEntity.ok().headers(responseHeaders).body(responseList);
            }
            else{
                responseHeaders.set("error", "You are not authorized to view this information.");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).headers(responseHeaders).build();
            }
        }

        catch (Exception e){
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("error", "Cannot fetch list of donors right now! please try again later.");
            return ResponseEntity.notFound().headers(responseHeaders).build();
        }
    }

}