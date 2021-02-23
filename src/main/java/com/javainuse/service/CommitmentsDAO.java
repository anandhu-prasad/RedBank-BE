package com.javainuse.service;


import com.javainuse.models.*;
import com.javainuse.repositories.*;
import com.javainuse.responses.MyCommitment_RespBody_donation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

                            MyCommitment_RespBody_donation donationDetails = new MyCommitment_RespBody_donation(donationInvitedDonors.getResponseTimeStamp(), "donation", "blood bank", obj4.getUserId(), profileBbRepo.findByUserId(obj4.getUserId()).getPhone1(), obj4.getAddress() + ", " + obj4.getState() + ", " +obj4.getDistrict() + ", " + obj4.getPincode(), true);
                            obj3.add(donationDetails);
                    }
                    else{
                            MyCommitment_RespBody_donation donationDetails = new MyCommitment_RespBody_donation(donationInvitedDonors.getResponseTimeStamp(), "donation", "hospital", obj4.getUserId(), profileHosRepo.findByUserId(obj4.getUserId()).getPhone1(), obj4.getAddress() + ", " + obj4.getState() + ", " +obj4.getDistrict() + ", " + obj4.getPincode(), true);
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

                            MyCommitment_RespBody_donation donationDetails = new MyCommitment_RespBody_donation(driveInvitedDonors.getResponseTimeStamp(), "drive", "blood bank", obj4.getUserId(), profileBbRepo.findByUserId(obj4.getUserId()).getPhone1(), obj4.getAddress() + ", " + obj4.getState() + ", " +obj4.getDistrict() + ", " + obj4.getPincode(), true);
                            obj3.add(donationDetails);
                    }
                    else{
                            MyCommitment_RespBody_donation donationDetails = new MyCommitment_RespBody_donation(driveInvitedDonors.getResponseTimeStamp(), "drive", "hospital", obj4.getUserId(), profileHosRepo.findByUserId(obj4.getUserId()).getPhone1(), obj4.getAddress() + ", " + obj4.getState() + ", " +obj4.getDistrict() + ", " + obj4.getPincode(), true);
                            obj3.add(donationDetails);
                    }
                }
            }
        }

        return obj3;
    }
}
