package com.javainuse.service;

import com.javainuse.models.*;
import com.javainuse.repositories.*;
import com.javainuse.requests.EmailReqBody;
import com.javainuse.requests.VerifyOtp_ReqBody;
import com.javainuse.util.OtpGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import com.javainuse.responses.SuccessResponseBody;

import java.util.Random;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    ProfileIndRepo profileIndRepo;

    @Autowired
    ProfileBbRepo profileBbRepo;

    @Autowired
    ProfileHosRepo profileHosRepo;

    @Autowired
    OtpRepo otpRepo;

//    @Autowired
//    OtpGenerator otpGenerator;

    public ResponseEntity<SuccessResponseBody> getOtp(String userEmail){


        ProfileInd userInd = profileIndRepo.findByEmail(userEmail);
        ProfileHos userHos = profileHosRepo.findByEmail(userEmail);
        ProfileBb userBb = profileBbRepo.findByEmail(userEmail);

        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);

//        int otp = Integer.parseInt(otpGenerator.generate(6));


        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(userEmail);
        msg.setSubject("Change Password Request");
        msg.setText("Hello , your otp for password reset is  \n"+otp+" \n Don't share this with anyone");

        HttpHeaders responseHeaders = new HttpHeaders();

        if(userInd != null){
            EmailOtpMapping obj = new EmailOtpMapping(userEmail, otp);
            otpRepo.save(obj);
            javaMailSender.send(msg);
            responseHeaders.set("success", "true");
            return ResponseEntity.ok().headers(responseHeaders).body(new SuccessResponseBody(true));
        }
        else if(userHos != null){
            EmailOtpMapping obj = new EmailOtpMapping(userEmail, otp);
            otpRepo.save(obj);
            javaMailSender.send(msg);
            responseHeaders.set("success", "true");
            return ResponseEntity.ok().headers(responseHeaders).body(new SuccessResponseBody(true));
        }
        else if(userBb != null){
            EmailOtpMapping obj = new EmailOtpMapping(userEmail, otp);
            otpRepo.save(obj);
            javaMailSender.send(msg);
            responseHeaders.set("success", "true");
            return ResponseEntity.ok().headers(responseHeaders).body(new SuccessResponseBody(true));
        }
        else{
            responseHeaders.set("success", "false");
            return ResponseEntity.ok().headers(responseHeaders).body(new SuccessResponseBody(false));
        }

    }

    public ResponseEntity<SuccessResponseBody> verifyOtp(VerifyOtp_ReqBody data){

        EmailOtpMapping otpData = otpRepo.findByUserEmail(data.getUserEmail());

        HttpHeaders responseHeaders = new HttpHeaders();

        if(data.getOtp() == otpData.getOtpReceived()){
            otpRepo.deleteById(otpData.getUserEmail());
            responseHeaders.set("success", "true");
            return ResponseEntity.ok().headers(responseHeaders).body(new SuccessResponseBody(true));
        }
        else{
            responseHeaders.set("success", "false");
            return ResponseEntity.ok().headers(responseHeaders).body(new SuccessResponseBody(false));
        }
    }
}
