package com.javainuse.controller;

import com.javainuse.requests.EmailReqBody;
import com.javainuse.requests.VerifyOtp_ReqBody;
import com.javainuse.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import com.javainuse.responses.SuccessResponseBody;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(path="/email")
public class SendEmailController {

    @Autowired
    EmailService emailService;
    //This POST mapping is for generating the OTP and sending of the OTP over the mail to the user
    @PostMapping("/sendotp")
    public ResponseEntity<SuccessResponseBody> sendOtp(@RequestBody EmailReqBody userEmail) {
       return  emailService.getOtp(userEmail.getUserEmail());
    }
    //This POST mapping is for verifying the OTP when entered by the user
    @PostMapping("/verifyotp")
    public ResponseEntity<SuccessResponseBody> verifyOtp(@RequestBody VerifyOtp_ReqBody data){
        return  emailService.verifyOtp(data);
    }

}
