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

    @Autowired
    AndroidPushNotificationsService androidPushNotificationsService;


    public ResponseEntity<?> expireRequest(ExpireRequestBody expireRequestBody, String userId){
        try{
            HttpHeaders responseHeaders = new HttpHeaders();
            DonationRequest donationRequest = donationRequestRepo.findByDonationId(expireRequestBody.getDonationId());

            if(userId.equals(donationRequest.getUserId())){
                donationRequest.setStatus(false);
                donationRequestRepo.save(donationRequest);

                responseHeaders.set("success", "true");
                return ResponseEntity.ok().headers(responseHeaders).body(new SuccessResponseBody(true));
            }else{
                responseHeaders.set("error", "unauthorized");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).headers(responseHeaders).build();
            }

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

    public ResponseEntity<List<DonationDonorsList_RespBody>> getDonationDonorDetails(String donationId, String userId) {

        HttpHeaders responseHeaders = new HttpHeaders();
       String orgId = donationRequestRepo.findByDonationId(donationId).getUserId();

       if( orgId.equals(userId)){

           List<DonationDonorsList_RespBody> donationDonorsLists = new ArrayList<>();
           List<DonationInvitedDonors> donationInvitedDonorsList = donationInvitedDonorsRepo.findByDonationId(donationId);
           for (DonationInvitedDonors donationInvitedDonors : donationInvitedDonorsList) {
//            if(donationInvitedDonors.getAcceptance() == 1){
               ProfileInd profileInd = profileIndRepo.findByUserId(donationInvitedDonors.getUserId());
               donationDonorsLists.add(new DonationDonorsList_RespBody(donationInvitedDonors.getUserId(), profileInd.getName(), profileInd.getBloodGroup(), donationInvitedDonors.isDonation_status(), donationInvitedDonors.getAcceptance()));

//            }
           }

           responseHeaders.set("success", "true");
           return ResponseEntity.ok().headers(responseHeaders).body(donationDonorsLists);

       }else{
           responseHeaders.set("error", "unauthorized");
           return ResponseEntity.status(HttpStatus.UNAUTHORIZED).headers(responseHeaders).build();
       }

    }

    public ResponseEntity<?> donationDonorVerify(DonationDonorVerification_ReqBody donationDonorVerification_ReqBody, String userId){
        try{

            DonationRequest donationRequest = donationRequestRepo.findByDonationId(donationDonorVerification_ReqBody.getDonationId());

            if( userId.equals(donationRequest.getUserId()))
            {
                DonationInvitedDonors donationInvitedDonors = donationInvitedDonorsRepo.findByDonationIdAndUserId(donationDonorVerification_ReqBody.getDonationId(), donationDonorVerification_ReqBody.getUserId());
                donationInvitedDonors.setDonation_status(true);
                donationInvitedDonors.setDonationDate(new Timestamp(System.currentTimeMillis()));
                donationInvitedDonorsRepo.save(donationInvitedDonors);

                //? UPDATING THE USER'S PROFILE.
                ProfileInd profileInd = profileIndRepo.findByUserId(donationDonorVerification_ReqBody.getUserId());
                profileInd.setDonorStatus(2);
                profileInd.setLast_donation_date(new Timestamp(System.currentTimeMillis()));
                profileIndRepo.save(profileInd);

                List<DonationInvitedDonors> donorsList =  donationInvitedDonorsRepo.findByDonationId(donationDonorVerification_ReqBody.getDonationId());

                boolean flag = true;

                for( DonationInvitedDonors item : donorsList){
                    if(item.getAcceptance() == 2){
                        flag = false;
                        break;
                    }
                    else{
                        if(item.getAcceptance() == 1 && item.isDonation_status() == false){
                            flag = false;
                            break;
                        }

                    }
                }

                if(flag){
                    DonationRequest obj =  donationRequestRepo.findByDonationId(donationDonorVerification_ReqBody.getDonationId());
                    obj.setStatus(false);
                    donationRequestRepo.save(obj);
                }

                //? SENDING NOTIFICATION TO THE DONOR.
                Notification notification = new Notification(donationDonorVerification_ReqBody.getUserId(), "Donation complete", "Your blood donation for Donation Id " + donationDonorVerification_ReqBody.getDonationId() + " is approved by the recipient.", new Timestamp(System.currentTimeMillis()));
                notificationRepo.save(notification);

                // FIREBASE NOTIFICATION
                JSONObject body = new JSONObject();
                body.put("to", "/topics/" + donationDonorVerification_ReqBody.getUserId());
                body.put("priority", "high");

                JSONObject firebaseNotification = new JSONObject();
                firebaseNotification.put("title", "Donation Verified");
                firebaseNotification.put("body", "Your donation for the donation request : " + donationDonorVerification_ReqBody.getDonationId()  + " has been verified by the organizer.");


                JSONObject data = new JSONObject();


                body.put("notification", firebaseNotification);
                body.put("data", data);

                HttpEntity request = new HttpEntity<>(body.toString());

                CompletableFuture pushNotification = androidPushNotificationsService.send(request);
                CompletableFuture.allOf(pushNotification).join();


                HttpHeaders responseHeaders = new HttpHeaders();
                responseHeaders.set("success", "true");
                return ResponseEntity.ok().headers(responseHeaders).body(new SuccessResponseBody(true));
            }else{
                HttpHeaders responseHeaders = new HttpHeaders();
                responseHeaders.set("error", "unauthorized");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).headers(responseHeaders).build();
            }


        }
        catch(Exception e){
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("error", "This status is not set right now, please try again later.");
            return ResponseEntity.notFound().headers(responseHeaders).build();
        }
    }

}
