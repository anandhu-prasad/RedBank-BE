package com.javainuse.service;

import com.javainuse.models.*;
import com.javainuse.repositories.*;
import com.javainuse.requests.InviteRequestBody;
import com.javainuse.responses.InviteDonationResponseBody;
import com.javainuse.responses.InviteDriveResponseBody;
import com.javainuse.responses.SuccessResponseBody;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class InvitesDAO {



    @Autowired
    DonationInvitedDonorsRepo donationInvitedDonorsRepo;

    @Autowired
    DriveInvitedDonorsRepo driveInvitedDonorsRepo;

    @Autowired
    ProfileIndRepo profileIndRepo;

    @Autowired
    ProfileHosRepo profileHosRepo;

    @Autowired
    ProfileBbRepo profileBbRepo;

    @Autowired
    NotificationRepo notificationRepo;

    @Autowired
    DrivesRepo drivesRepo;

    @Autowired
    DonationRequestRepo donationRequestRepo;

    public ResponseEntity<List<?>> fetchInvites(String userId){
        try{

            List <Object> responseList = new ArrayList<>();
            Timestamp currTime = new Timestamp(System.currentTimeMillis());

            //? GETTING THE LIST OF DONATION REQUESTS FROM drive_invited_donors TABLE.

            List <DonationInvitedDonors> donationInvitedDonors = donationInvitedDonorsRepo.findByUserId(userId);

            List <DriveInvitedDonors> driveInvitedDonors = driveInvitedDonorsRepo.findByUserId(userId);

            for (DonationInvitedDonors donationInvitedDonor : donationInvitedDonors) {


                if (donationInvitedDonor.getAcceptance() == 2) {

                    String donationId = donationInvitedDonor.getDonationId();
                    DonationRequest donationRequest = donationRequestRepo.findByDonationId(donationId);

                    if(donationRequest.getStatus()){

                        String recipientName;
                        String recipientType;
                        String recipientEmail;
                        String recipientContact;

                        if(donationRequest.getUserId().substring(0, 3).equals("HOS")){
                            recipientType = "Hospital";
                            ProfileHos profileHos = profileHosRepo.findByUserId(donationRequest.getUserId());
                            recipientName = profileHos.getName();
                            recipientEmail = profileHos.getEmail();
                            recipientContact = profileHos.getPhone1();
                        }
                        else if(donationRequest.getUserId().substring(0, 3).equals("BOB")){
                            recipientType = "Blood Bank";
                            ProfileBb profileBb = profileBbRepo.findByUserId(donationRequest.getUserId());
                            recipientName = profileBb.getName();
                            recipientEmail = profileBb.getEmail();
                            recipientContact = profileBb.getPhone1();
                        }
                        else{
                            recipientType = "Individual";
                            ProfileInd profileInd = profileIndRepo.findByUserId(donationRequest.getUserId());
                            recipientName = profileInd.getName();
                            recipientEmail = profileInd.getEmail();
                            recipientContact = profileInd.getPhone();
                        }


                        InviteDonationResponseBody inviteDonationResponseBody = new InviteDonationResponseBody(donationRequest.getRequestTime(), donationId, donationInvitedDonor.getAcceptance(), recipientName, recipientType, recipientEmail, recipientContact, donationRequest.getAddress());
                        responseList.add(inviteDonationResponseBody);

                    }}
            }


            for (DriveInvitedDonors driveInvitedDonor : driveInvitedDonors) {


                if (driveInvitedDonor.getAcceptance() == 2) {
                    String driveId = driveInvitedDonor.getDriveId();
                    Drives drive = drivesRepo.findByDriveId(driveId);


                    //? IF THE DRIVE IS ACTIVE AND START TIMESTAMP > CURRENT DATE
                    Date start = new Date();
                    start.setTime(drive.getStartTimestamp().getTime());
                    Date current = new Date();
                    current.setTime(new Timestamp(System.currentTimeMillis()).getTime());

                    if(drive.getStatus() && start.compareTo(current) > 0){

                        String recipientName;
                        String recipientType;
                        String recipientEmail;
                        String recipientContact;

                        if(drive.getUserId().substring(0, 3).equals("HOS")){
                            recipientType = "Hospital";
                            ProfileHos profileHos = profileHosRepo.findByUserId(drive.getUserId());
                            recipientName = profileHos.getName();
                            recipientEmail = profileHos.getEmail();
                            recipientContact = profileHos.getPhone1();
                        }
                        else{
                            recipientType = "Blood Bank";
                            ProfileBb profileBb = profileBbRepo.findByUserId(drive.getUserId());
                            recipientName = profileBb.getName();
                            recipientEmail = profileBb.getEmail();
                            recipientContact = profileBb.getPhone1();
                        }

                        InviteDriveResponseBody inviteDriveResponseBody = new InviteDriveResponseBody(drive.getRequestTime(), driveId, driveInvitedDonor.getAcceptance(), recipientName, recipientType, recipientEmail, recipientContact, drive.getAddress(), drive.getDistrict(), drive.getState(), drive.getPincode(), drive.getStartTimestamp(), drive.getEndTimestamp(), drive.getMessage());
                        responseList.add(inviteDriveResponseBody);
                    }
                }
            }

            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("success", "true");
            System.out.println("Okayy");
            System.out.println(responseList);
            return ResponseEntity.ok().headers(responseHeaders).body(responseList);
        }

        catch(Exception e){
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("error", "Something went wrong, please try again later.");
            return ResponseEntity.badRequest().headers(responseHeaders).build();

        }
    }

    public ResponseEntity<SuccessResponseBody> setInviteResponse(InviteRequestBody inviteRequestBody, String userId){


        try{
            if(inviteRequestBody.getEventType().equals("drive")){
                //? MAKE A NEW ID OBJECT

                DriveInvitedDonors driveInvitedDonors = driveInvitedDonorsRepo.findByDriveIdAndUserId(inviteRequestBody.getEventId(), userId);

                driveInvitedDonors.setAcceptance(inviteRequestBody.getAcceptance());
                driveInvitedDonors.setResponseTimeStamp(new Timestamp(System.currentTimeMillis()));
                driveInvitedDonors.setRejection_message(inviteRequestBody.getRejectionMessage());

                driveInvitedDonorsRepo.save(driveInvitedDonors);

                //? ALSO, TO SET THE NOTIFICATION, WE HAVE,

                //?1.  GET THE NAME OF THE DONOR BY FIRST FINDING THE DONATION REQUEST/ DRIVE ROW IN THE TABLE, GET THE ORGANIZER'S USER ID,
                //? THEN SET A NOTIFICATION FOR THAT ID.
                if(inviteRequestBody.getAcceptance() == 1){
                    String organizerId = drivesRepo.findByDriveId(inviteRequestBody.getEventId()).getUserId();

                    String donorName = profileIndRepo.findByUserId(userId).getName();

                    Notification notification = new Notification(organizerId, "New donor!", donorName + " has accepted your drive invite.", new Timestamp(System.currentTimeMillis()));
                    notificationRepo.save(notification);}

            }
            else{

                DonationInvitedDonors donationInvitedDonors = donationInvitedDonorsRepo.findByDonationIdAndUserId(inviteRequestBody.getEventId(), userId);

                donationInvitedDonors.setAcceptance(inviteRequestBody.getAcceptance());
                donationInvitedDonors.setResponseTimeStamp(new Timestamp(System.currentTimeMillis()));
                donationInvitedDonors.setRejection_message(inviteRequestBody.getRejectionMessage());

                donationInvitedDonorsRepo.save(donationInvitedDonors);

                //? ALSO, TO SET THE NOTIFICATION, WE HAVE,

                //?1.  GET THE NAME OF THE DONOR BY FIRST FINDING THE DONATION REQUEST/ DRIVE ROW IN THE TABLE, GET THE ORGANIZER'S USER ID,
                //? THEN SET A NOTIFICATION FOR THAT ID.
                if(inviteRequestBody.getAcceptance() == 1){
                    String organizerId = donationRequestRepo.findByDonationId(inviteRequestBody.getEventId()).getUserId();

                    String donorName = profileIndRepo.findByUserId(userId).getName();

                    Notification notification = new Notification(organizerId, "New donor!", donorName + " has accepted your donation request.", new Timestamp(System.currentTimeMillis()));
                    notificationRepo.save(notification);}

            }

            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("success", "true");
            return ResponseEntity.ok().headers(responseHeaders).body(new SuccessResponseBody(true));
        }
        catch (Exception e){
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("error", "Something went wrong, please try again later!");
            return ResponseEntity.notFound().headers(responseHeaders).build();
        }

    }

}
