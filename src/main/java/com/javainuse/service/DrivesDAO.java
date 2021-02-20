package com.javainuse.service;



import com.javainuse.models.*;
import com.javainuse.repositories.DriveInvitedDonorsRepo;
import com.javainuse.repositories.DrivesRepo;
import com.javainuse.repositories.NotificationRepo;
import com.javainuse.repositories.ProfileIndRepo;
import com.javainuse.requests.ConductADrive_ReqBody;
import com.javainuse.responses.SuccessResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class DrivesDAO {

    @Autowired
    DrivesRepo drivesRepo;

    @Autowired
    NotificationRepo notificationRepo;

    @Autowired
    ProfileIndRepo profileIndRepo;

    @Autowired
    DriveInvitedDonorsRepo driveInvitedDonorRepo;


    public List<ProfileInd> individuals = new ArrayList<>(); // list to store individual details

    public ResponseEntity<SuccessResponseBody> saveDriveDetails(ConductADrive_ReqBody data, String userId, int userType) {

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
        for (int i = 0; i < individuals.size(); i++) {
            DriveInvitedDonors obj2 = new DriveInvitedDonors(driveId, individuals.get(i).getUserId());   // 2 -> pending, 0 -> rejected
            driveInvitedDonorRepo.save(obj2);
        }

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("success", "true");

        return ResponseEntity.ok().headers(responseHeaders).body(new SuccessResponseBody(true));


    }
}
