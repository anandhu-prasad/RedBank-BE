package com.javainuse.controller;

import com.javainuse.config.JwtTokenUtil;
import com.javainuse.models.DonationRequest;
import com.javainuse.repositories.DonationRequestRepo;
import com.javainuse.requests.ExpireRequestBody;
import com.javainuse.responses.SuccessResponseBody;
import com.javainuse.service.DonationRequestDAO;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/donationrequests")
public class DonationRequestsController {

    @Autowired
    DonationRequestDAO donationRequestDAO;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    //? TO EXPIRE A DONATION REQUEST MADE BY CURRENT USER.
    //! TESTED
    @PutMapping("/expirerequest")
    public ResponseEntity<SuccessResponseBody> expireRequest(@RequestBody ExpireRequestBody expireRequestBody){
        return donationRequestDAO.expireRequest(expireRequestBody);
    }

    //? TO FETCH THE LIST OF DONATION REQUESTS OF THE CURRENT USER.
    //! TESTED
    @GetMapping("/fetchrequests")
    public ResponseEntity<List<DonationRequest>> fetchDonationRequests(@RequestHeader ("Authorization") String userToken){

        Claims claims = jwtTokenUtil.getAllClaimsFromToken(userToken.substring(7));
        String userId = claims.get("userId").toString();
        return donationRequestDAO.fetchDonationRequests(userId);
    }
}