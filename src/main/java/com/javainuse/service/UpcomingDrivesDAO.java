package com.javainuse.service;


import com.javainuse.models.Drives;
import com.javainuse.models.ProfileBb;
import com.javainuse.models.ProfileHos;
import com.javainuse.repositories.DrivesRepo;
import com.javainuse.repositories.ProfileBbRepo;
import com.javainuse.repositories.ProfileHosRepo;
import com.javainuse.requests.UpcomingDrives_ReqBody;
import com.javainuse.responses.UpcomingDrives_RespBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


    public List<UpcomingDrives_RespBody> getDrives(UpcomingDrives_ReqBody data) {

        List<Drives> driveList = new ArrayList<>();
        List<UpcomingDrives_RespBody> resultsList = new ArrayList<>();

        driveList = drivesRepo.findAll();

        // filtering the data  on basis of state ( because it's mandatory ), district , pincode
        if (!data.getState().equals("All")) {
            driveList = driveList.stream().filter(item -> item.getState().equals(data.getState())).collect(Collectors.toList());
        }
        if (!(data.getDistrict().equals("All") || data.getDistrict().equals(""))) {
            driveList = driveList.stream().filter(item -> item.getDistrict().equals(data.getDistrict())).collect(Collectors.toList());
            System.out.println("b");
        }
        if (data.getPincode() != null) {
            driveList = driveList.stream().filter(item -> item.getPincode() == data.getPincode()).collect(Collectors.toList());
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
}
