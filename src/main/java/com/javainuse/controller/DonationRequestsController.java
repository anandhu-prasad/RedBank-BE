package com.javainuse.controller;

import com.javainuse.config.JwtTokenUtil;
import com.javainuse.models.DonationRequest;
import com.javainuse.repositories.DonationRequestRepo;
import com.javainuse.requests.ExpireRequestBody;
import com.javainuse.responses.SuccessResponseBody;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/donationrequests")
public class DonationRequestsController {

    @Autowired
    DonationRequestRepo donationRequestRepo;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    //? TO EXPIRE A DONATION REQUEST MADE BY CURRENT USER.

    @PutMapping("/expirerequest")
    public ResponseEntity<SuccessResponseBody> expireRequest(@RequestBody ExpireRequestBody expireRequestBody){

        //! TESTED
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

    //? TO FETCH THE LIST OF DONATION REQUESTS OF THE CURRENT USER.
    //! TESTED

    @GetMapping("/fetchrequests")
    public ResponseEntity<List<DonationRequest>> fetchDonationRequests(@RequestHeader ("Authorization") String userToken){
        try{
            //! HARDCODED DATA FOR NOW, USER ID TO BE EXTRACTED FROM TOKEN - DONE
            Claims claims = jwtTokenUtil.getAllClaimsFromToken(userToken.substring(7));

            String userId = claims.get("userId").toString();

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

//
//    //?///////////////////////////////// ANANDHU | FOR TESTING ONLY | REMOVE IN PROD
//
//    //! WORKS
//
//    @PostMapping("testaddrequest")
//    public ResponseEntity<DonationRequest> addNewRequest(@RequestBody DonationRequest donationRequest){
//        HttpHeaders responseHeaders = new HttpHeaders();
//        responseHeaders.set("success", "true");
//        return ResponseEntity.ok().headers(responseHeaders).body(donationRequestRepo.save(donationRequest));
//    }
//
//
//    //?////////////////////////////////////////////////////////////
//

}