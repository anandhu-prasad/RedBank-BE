package com.javainuse.service;

import com.javainuse.models.DonationInvitedDonors;
import com.javainuse.models.DonationRequest;
import com.javainuse.models.Notification;
import com.javainuse.models.ProfileInd;
import com.javainuse.repositories.DonationInvitedDonorsRepo;
import com.javainuse.repositories.DonationRequestRepo;
import com.javainuse.repositories.NotificationRepo;
import com.javainuse.repositories.ProfileIndRepo;
import com.javainuse.requests.DonationDonorVerification_ReqBody;
import com.javainuse.requests.ExpireRequestBody;
import com.javainuse.responses.DonationDonorsList_RespBody;
import com.javainuse.responses.SuccessResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class DonationRequestDAO {

    @Autowired
    DonationRequestRepo donationRequestRepo;

    @Autowired
    DonationInvitedDonorsRepo donationInvitedDonorsRepo;

    @Autowired
    ProfileIndRepo profileIndRepo;

    @Autowired
    NotificationRepo notificationRepo;


    public ResponseEntity<SuccessResponseBody> expireRequest(ExpireRequestBody expireRequestBody){
        try{
            DonationRequest donationRequest = donationRequestRepo.findByDonationId(expireRequestBody.getDonationId());
            donationRequest.setStatus(false);

            donationRequestRepo.save(donationRequest);

            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("success", "true");
            return ResponseEntity.ok().headers(responseHeaders).body(new SuccessResponseBody(true));
        }
        catch(Exception e){
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("error", "This request cannot be expired right now, please try again later.");
            return ResponseEntity.notFound().headers(responseHeaders).build();
        }

    }

    public ResponseEntity<List<DonationRequest>> fetchDonationRequests(String userId){
        try{
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("success", "true");
            return ResponseEntity.ok().headers(responseHeaders).body(donationRequestRepo.findByUserId(userId));
        }
        catch (Exception e){
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("error", "Error accessing the requests, Please try again later.");
            return ResponseEntity.notFound().headers(responseHeaders).build();
        }
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public List<DonationDonorsList_RespBody> getDonationDonorDetails(String donationId) {

        List<DonationDonorsList_RespBody> donationDonorsLists = new ArrayList<>();
        List<DonationInvitedDonors> donationInvitedDonorsList = donationInvitedDonorsRepo.findByDonationId(donationId);
        for (DonationInvitedDonors donationInvitedDonors : donationInvitedDonorsList) {
            ProfileInd profileInd = profileIndRepo.findByUserId(donationInvitedDonors.getUserId());
            donationDonorsLists.add(new DonationDonorsList_RespBody(donationInvitedDonors.getUserId(), profileInd.getName(), profileInd.getBloodGroup(), donationInvitedDonors.isDonation_status()));

        }
        return donationDonorsLists;
    }

    public ResponseEntity<SuccessResponseBody> donationDonorVerify(DonationDonorVerification_ReqBody donationDonorVerification_ReqBody){
        try{

            System.out.println(donationDonorVerification_ReqBody.getUserId());

            DonationInvitedDonors donationInvitedDonors = donationInvitedDonorsRepo.findByDonationIdAndUserId(donationDonorVerification_ReqBody.getDonationId(), donationDonorVerification_ReqBody.getUserId());
            donationInvitedDonors.setDonation_status(true);
            donationInvitedDonors.setDonationDate(new Timestamp(System.currentTimeMillis()));
            donationInvitedDonorsRepo.save(donationInvitedDonors);

            System.out.println(donationDonorVerification_ReqBody.getUserId());

            //? UPDATING THE USER'S PROFILE.
            ProfileInd profileInd = profileIndRepo.findByUserId(donationDonorVerification_ReqBody.getUserId());
            profileInd.setDonorStatus(2);
            profileInd.setLast_donation_date(new Timestamp(System.currentTimeMillis()));
            profileIndRepo.save(profileInd);

            System.out.println("two");
            //? SENDING NOTIFICATION TO THE DONOR.
            Notification notification = new Notification(donationDonorVerification_ReqBody.getUserId(), "Donation complete", "Your blood donation for Donation Id" + donationDonorVerification_ReqBody.getDonationId() + " is approved by the recipient.", new Timestamp(System.currentTimeMillis()));
            notificationRepo.save(notification);

            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("success", "true");
            return ResponseEntity.ok().headers(responseHeaders).body(new SuccessResponseBody(true));
        }
        catch(Exception e){
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("error", "This status is not set right now, please try again later.");
            return ResponseEntity.notFound().headers(responseHeaders).build();
        }
    }

}
