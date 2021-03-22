package com.javainuse.controller;

import com.javainuse.requests.EmailReqBody;
import com.javainuse.requests.VerifyOtp_ReqBody;
import com.javainuse.responses.SuccessResponseBody;
import com.javainuse.service.EmailVerificationDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/verification")
//This controller is for sending and verification of the OTP when the user clicks on Forgot password
public class EmailVerificationController {
    @Autowired
    EmailVerificationDAO emailVerificationDAO;
    //This Post Mapping is for generating 6 digit OTP and sending of the OTP in the user's mail
    @PostMapping("/sendotp")
    public ResponseEntity<SuccessResponseBody> sendOtp(@RequestBody EmailReqBody emailReq){
        return emailVerificationDAO.sendOtp(emailReq.getUserEmail());
    }
    //This Post Mapping is for verifying the OTP when the user enters the 6 digit OTP send to user in their respective emails.
    @PostMapping("/verifyotp")
    public ResponseEntity<SuccessResponseBody> verifyOtp(@RequestBody VerifyOtp_ReqBody verifyOtp_ReqBody){
        return emailVerificationDAO.verifyOtp(verifyOtp_ReqBody.getUserEmail(), verifyOtp_ReqBody.getOtp());
    }
}
