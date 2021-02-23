package com.javainuse.service;


import com.javainuse.models.*;
import com.javainuse.repositories.*;
import com.javainuse.responses.MyCommitment_RespBody_donation;
import com.javainuse.responses.MyCommitment_RespBody_drive;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.javainuse.models.DonationInvitedDonors;
import com.javainuse.models.DriveInvitedDonors;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommitmentsDAO {

    @Autowired
    DonationInvitedDonorsRepo donationInvitedDonorsRepo;

    @Autowired
    DriveInvitedDonorsRepo driveInvitedDonorsRepo;

    @Autowired
    DrivesRepo drivesRepo;

    @Autowired
    DonationRequestRepo donationRequestRepo;

    @Autowired
    ProfileBbRepo profileBbRepo;

    @Autowired
    ProfileHosRepo profileHosRepo;

    @Autowired
    ProfileIndRepo profileIndRepo;


    public List<?> getDetails(String userId){

        List<DonationInvitedDonors> obj = new ArrayList<>();
        List<DriveInvitedDonors> obj2 = new ArrayList<>();
        List<Object> obj3 = new ArrayList<>();
        obj = donationInvitedDonorsRepo.findByUserId(userId);    //accessing the 'donation_invited_donor' table
        obj2 =  driveInvitedDonorsRepo.findByUserId(userId);     //accessing the 'drive_invited_donor' table

        for (DonationInvitedDonors donationInvitedDonors : obj) {
            if (donationInvitedDonors.getAcceptance() == 1) {
                DonationRequest obj4 = donationRequestRepo.findByDonationId(donationInvitedDonors.getDonationId());  //accessing the 'donation_request' table

                if (obj4.getStatus()) {
                    if(obj4.getUserId().substring(0, 3).equals("BOB")){

                            MyCommitment_RespBody_donation donationDetails = new MyCommitment_RespBody_donation(donationInvitedDonors.getResponseTimeStamp(),
                                    "donation", "blood bank", profileBbRepo.findByUserId(obj4.getUserId()).getName(),
                                    profileBbRepo.findByUserId(obj4.getUserId()).getPhone1(), profileBbRepo.findByUserId(obj4.getUserId()).getEmail(),
                                    obj4.getAddress() + ", " + obj4.getState() + ", " +obj4.getDistrict() + ", " + obj4.getPincode(),
                                    true, obj4.getDonationId());
                            obj3.add(donationDetails);
                    }
                    else if(obj4.getUserId().substring(0, 3).equals("HOS"))
                        {
                            MyCommitment_RespBody_donation donationDetails = new MyCommitment_RespBody_donation(donationInvitedDonors.getResponseTimeStamp(),
                                    "donation", "hospital", profileHosRepo.findByUserId(obj4.getUserId()).getName(),
                                    profileHosRepo.findByUserId(obj4.getUserId()).getPhone1(), profileHosRepo.findByUserId(obj4.getUserId()).getEmail(),
                                    obj4.getAddress() + ", " + obj4.getState() + ", " +obj4.getDistrict() + ", " + obj4.getPincode(),
                                    true, obj4.getDonationId());
                            obj3.add(donationDetails);
                    }
                    else{
                        MyCommitment_RespBody_donation donationDetails = new MyCommitment_RespBody_donation(donationInvitedDonors.getResponseTimeStamp(),
                                "donation", "individual", profileIndRepo.findByUserId(obj4.getUserId()).getName(),
                                profileIndRepo.findByUserId(obj4.getUserId()).getPhone(), profileIndRepo.findByUserId(obj4.getUserId()).getEmail(),
                                obj4.getAddress() + ", " + obj4.getState() + ", " +obj4.getDistrict() + ", " + obj4.getPincode(),
                                true, obj4.getDonationId());
                        obj3.add(donationDetails);
                    }
                }
            }
        }


        for (DriveInvitedDonors driveInvitedDonors : obj2) {
            if (driveInvitedDonors.getAcceptance() == 1) {
                Drives obj4 = drivesRepo.findByDriveId(driveInvitedDonors.getDriveId());  //accessing the 'drives' table
                if (obj4.getStatus()) {
                    if(obj4.getUserId().substring(0, 3).equals("BOB")){

                            MyCommitment_RespBody_drive driveDetails = new MyCommitment_RespBody_drive(driveInvitedDonors.getResponseTimeStamp(),
                                    "drive", "blood bank", profileBbRepo.findByUserId(obj4.getUserId()).getName(),
                                    profileBbRepo.findByUserId(obj4.getUserId()).getPhone1(), profileBbRepo.findByUserId(obj4.getUserId()).getEmail() ,
                                    obj4.getAddress() + ", " + obj4.getState() + ", " +obj4.getDistrict() + ", " + obj4.getPincode(),
                                    true, obj4.getStartTimestamp(), obj4.getEndTimestamp(), obj4.getDriveId());
                            obj3.add(driveDetails);
                    }
                    else{
                            MyCommitment_RespBody_drive driveDetails = new MyCommitment_RespBody_drive(driveInvitedDonors.getResponseTimeStamp(),
                                    "drive", "hospital", profileHosRepo.findByUserId(obj4.getUserId()).getName(),
                                    profileHosRepo.findByUserId(obj4.getUserId()).getPhone1(), profileHosRepo.findByUserId(obj4.getUserId()).getEmail(),
                                    obj4.getAddress() + ", " + obj4.getState() + ", " +obj4.getDistrict() + ", " + obj4.getPincode(),
                                    true, obj4.getStartTimestamp(),obj4.getEndTimestamp(), obj4.getDriveId());
                            obj3.add(driveDetails);
                    }
                }
            }
        }

        return obj3;

    }
}
