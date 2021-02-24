package com.javainuse.service;

import com.javainuse.models.DonationRequest;
import com.javainuse.repositories.DonationRequestRepo;
import com.javainuse.requests.ExpireRequestBody;
import com.javainuse.responses.SuccessResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DonationRequestDAO {

    @Autowired
    DonationRequestRepo donationRequestRepo;


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

}
