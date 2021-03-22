package com.javainuse.service;



import com.javainuse.models.*;
import com.javainuse.repositories.DriveInvitedDonorsRepo;
import com.javainuse.repositories.DrivesRepo;
import com.javainuse.repositories.NotificationRepo;
import com.javainuse.repositories.ProfileIndRepo;
import com.javainuse.requests.ConductADrive_ReqBody;
import com.javainuse.responses.SuccessResponseBody;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class ConductADriveDAO {


    @Autowired
    DrivesRepo drivesRepo;

    @Autowired
    NotificationRepo notificationRepo;

    @Autowired
    ProfileIndRepo profileIndRepo;

    @Autowired
    DriveInvitedDonorsRepo driveInvitedDonorRepo;

    @Autowired
    AndroidPushNotificationsService androidPushNotificationsService;


    public List<ProfileInd> individuals = new ArrayList<>(); // list to store individual details

    public ResponseEntity<SuccessResponseBody> saveDriveDetails(ConductADrive_ReqBody data, String userId, int userType) throws JSONException {


        // getting the current timestamp
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        // saving the details in the 'drives' table
        Drives obj4 = new Drives(userId, data.getAddress(), data.getState(), data.getDistrict(), data.getPincode(), data.getMessage(), timestamp, data.getStartTimeStamp(), data.getEndTimeStamp(), data.getBloodGroups().contains("B+"), data.getBloodGroups().contains("B-"),data.getBloodGroups().contains("A+"),data.getBloodGroups().contains("A-"),data.getBloodGroups().contains("O+"),data.getBloodGroups().contains("O-"),data.getBloodGroups().contains("AB+"),data.getBloodGroups().contains("AB-"));
        obj4.setStatus(true);
        Drives returnObj = drivesRepo.save(obj4);

        //saving the details in the 'notification' table
        Notification obj = new Notification(userId, "Donation drive", "Donation drive conducted successfully!", timestamp);
        obj.setStatus(false);   // setting the default status of notification -> false (not seen)
        notificationRepo.save(obj);

        // getting the details of the individuals matching the blood group criterion of 'conduct a drive' form
        individuals = profileIndRepo.findByBloodGroupIn(data.getBloodGroups());


        //getting the drive Id
        String driveId = returnObj.getDriveId();

        // saving the details in the 'drives_invited_donors';
        for (ProfileInd individual : individuals) {
            //saving the details of active donors only
            if(individual.getDonorStatus() != 2){
                DriveInvitedDonors obj2 = new DriveInvitedDonors(driveId, individual.getUserId(), 2);   // 2 -> pending, 0 -> rejected
                driveInvitedDonorRepo.save(obj2);

                // SENDING push notification
                JSONObject body = new JSONObject();
                body.put("to", "/topics/" + individual.getUserId());
                body.put("priority", "high");

                JSONObject notification = new JSONObject();
                notification.put("title", "Drive Invite");
                notification.put("body", "You have been invited to " + driveId + " drive . Please check My Invites Section for more details." );

                JSONObject data2 = new JSONObject();
                data2.put("Key-1", "test data 1");
                data2.put("Key-2", "test data 2");

                body.put("notification", notification);
                body.put("data", data2);
                HttpEntity request = new HttpEntity<>(body.toString());

                CompletableFuture pushNotification = androidPushNotificationsService.send(request);
                CompletableFuture.allOf(pushNotification).join();
            }



        }

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("success", "true");
        return ResponseEntity.ok().headers(responseHeaders).body(new SuccessResponseBody(true));

    }
}
