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
public class EmailVerificationController {
    @Autowired
    EmailVerificationDAO emailVerificationDAO;
    
    @PostMapping("/sendotp")
    public ResponseEntity<SuccessResponseBody> sendOtp(@RequestBody EmailReqBody emailReq){
        return emailVerificationDAO.sendOtp(emailReq.getUserEmail());
    }

    @PostMapping("/verifyotp")
    public ResponseEntity<SuccessResponseBody> verifyOtp(@RequestBody VerifyOtp_ReqBody verifyOtp_ReqBody){
        return emailVerificationDAO.verifyOtp(verifyOtp_ReqBody.getUserEmail(), verifyOtp_ReqBody.getOtp());
    }
}
