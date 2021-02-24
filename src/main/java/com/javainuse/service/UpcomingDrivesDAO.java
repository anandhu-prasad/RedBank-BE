package com.javainuse.service;


import com.javainuse.models.*;
import com.javainuse.repositories.*;
import com.javainuse.requests.UpcomingDrives_ReqBody;
import com.javainuse.responses.SuccessResponseBody;
import com.javainuse.responses.UpcomingDrives_RespBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UpcomingDrivesDAO {

    @Autowired
    DrivesRepo drivesRepo;

    @Autowired
    ProfileBbRepo profileBbRepo;

    @Autowired
    ProfileHosRepo profileHosRepo;

    @Autowired
    DriveInvitedDonorsRepo driveInvitedDonorsRepo;

    @Autowired
    NotificationRepo notificationRepo;

    @Autowired
    ProfileIndRepo profileIndRepo;


    public List<UpcomingDrives_RespBody> getDrives(UpcomingDrives_ReqBody data) {

        List<Drives> driveList = new ArrayList<>();
        List<UpcomingDrives_RespBody> resultsList = new ArrayList<>();

        driveList = drivesRepo.findAll();

        // filtering the data  on basis of state ( because it's mandatory ), district , pincode
        if (!data.getState().equals("All") && !data.getState().equals("Select state") && data.getState() != null) {
            driveList = driveList.stream().filter(item -> item.getState().equals(data.getState())).collect(Collectors.toList());
        }
        if (!(data.getDistrict().equals("All") || data.getDistrict().equals("") || data.getDistrict().equals("Select district") || data.getDistrict()  != null)) {
            driveList = driveList.stream().filter(item -> item.getDistrict().equals(data.getDistrict())).collect(Collectors.toList());
            System.out.println("b");
        }
        if (!data.getPincode().equals("") && data.getPincode() != null) {
            driveList = driveList.stream().filter(item -> item.getPincode().equals(data.getPincode())).collect(Collectors.toList());
            System.out.println("c");
        }


        for (Drives drives : driveList) {

            ProfileHos profileHos = new ProfileHos();
            ProfileBb profileBb = new ProfileBb();

            // fetching the name and contact of the organizer (HOS/BB)  from it's table using the user id
            if(drives.getUserId().substring(0,3).equals("BOB")){
                profileBb = profileBbRepo.findByUserId(drives.getUserId());
            }else{
                profileHos = profileHosRepo.findByUserId(drives.getUserId());
            }

            // list to store the invited blood groups by the drive
            List<String> bloodGroups = new ArrayList<>();

            if (drives.getaPos())
                bloodGroups.add("A+");
            if (drives.getaNeg())
                bloodGroups.add("A-");
            if (drives.getbPos())
                bloodGroups.add("B+");
            if (drives.getbNeg())
                bloodGroups.add("B-");
            if (drives.getoPos())
                bloodGroups.add("O+");
            if (drives.getoNeg())
                bloodGroups.add("O-");
            if (drives.getAbPos())
                bloodGroups.add("AB+");
            if (drives.getAbNeg())
                bloodGroups.add("AB-");


            //saving the results in the result list
            if(drives.getUserId().substring(0,3).equals("BOB")){
                resultsList.add(new UpcomingDrives_RespBody(profileBb.getName(), profileBb.getPhone1(), drives.getStartTimestamp(), drives.getEndTimestamp(), drives.getAddress(), drives.getState(), drives.getDistrict(), drives.getPincode(), bloodGroups));
            }else{
                resultsList.add(new UpcomingDrives_RespBody(profileHos.getName(), profileHos.getPhone1(), drives.getStartTimestamp(), drives.getEndTimestamp(), drives.getAddress(), drives.getState(), drives.getDistrict(), drives.getPincode(), bloodGroups));
            }

        }

        return resultsList;
    }

    public ResponseEntity<SuccessResponseBody> registerForDrive(String userId, String driveId){

        if(driveInvitedDonorsRepo.findByDriveIdAndUserId(driveId, userId) == null){

            DriveInvitedDonors driveInvitedDonors = new DriveInvitedDonors(driveId, userId);
            driveInvitedDonors.setAcceptance(1);
            driveInvitedDonors.setResponseTimeStamp(new Timestamp(System.currentTimeMillis()));
            System.out.println(driveId);
            driveInvitedDonorsRepo.save(driveInvitedDonors);

            //? SENDING NOTIFICATION TO THE ORGANIZER.

            String donorName = profileIndRepo.findByUserId(userId).getName();

            String organizerId = drivesRepo.findByDriveId(driveId).getUserId();

            notificationRepo.save(new Notification(organizerId, "New donor!", donorName + "has registered drive: " + driveId, new Timestamp(System.currentTimeMillis())));

            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("success", "true");
            return ResponseEntity.ok().headers(responseHeaders).body(new SuccessResponseBody(true));
        }
        else{
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("error", "You are already registered for this drive.");
            return ResponseEntity.ok().headers(responseHeaders).body(new SuccessResponseBody(false));
        }
    }

}
