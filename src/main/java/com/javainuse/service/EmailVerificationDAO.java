package com.javainuse.service;

import com.javainuse.models.*;
import com.javainuse.repositories.EmailVerificationRepo;
import com.javainuse.repositories.ProfileBbRepo;
import com.javainuse.repositories.ProfileHosRepo;
import com.javainuse.repositories.ProfileIndRepo;
import com.javainuse.responses.SuccessResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class EmailVerificationDAO {

    @Autowired
    EmailVerificationRepo emailVerificationRepo;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    ProfileIndRepo profileIndRepo;

    @Autowired
    ProfileBbRepo profileBbRepo;

    @Autowired
    ProfileHosRepo profileHosRepo;

    public ResponseEntity<SuccessResponseBody> sendOtp(String email){

        ProfileInd userInd = profileIndRepo.findByEmail(email);
        ProfileHos userHos = profileHosRepo.findByEmail(email);
        ProfileBb userBb = profileBbRepo.findByEmail(email);

        HttpHeaders responseHeaders = new HttpHeaders();

        try{
            if (userInd == null && userHos == null && userBb == null) {

                Random random = new Random();
                int otp = 100000 + random.nextInt(900000);

                SimpleMailMessage msg = new SimpleMailMessage();
                msg.setTo(email);
                msg.setSubject("Account Verification");
                msg.setText("Hello , Please verify your account with the following OTP.  \n" + otp + " \n Do not share this with anyone");

                EmailVerification obj = new EmailVerification(email, otp);
                emailVerificationRepo.save(obj);
                javaMailSender.send(msg);

                responseHeaders.set("success", "true");
                return ResponseEntity.ok().headers(responseHeaders).body(new SuccessResponseBody(true));
            } else {
                responseHeaders.set("error", "Email is already associated with another account.");
                return ResponseEntity.ok().headers(responseHeaders).body(new SuccessResponseBody(false));
            }
        }
        catch(Exception e){
            responseHeaders.set("error", "Something went wrong, please try again later.");
            return ResponseEntity.ok().headers(responseHeaders).body(new SuccessResponseBody(false));
        }
    }

    public ResponseEntity<SuccessResponseBody> verifyOtp(String email, int otp){
        HttpHeaders responseHeaders = new HttpHeaders();
       try{
           EmailVerification emailVerification = emailVerificationRepo.findByUserEmail(email);

           if(emailVerification.getOtpReceived() == otp){
               emailVerificationRepo.deleteById(email);
               responseHeaders.set("success", "true");
               return ResponseEntity.ok().headers(responseHeaders).body(new SuccessResponseBody(true));
           }
           else{
               responseHeaders.set("error", "Invalid OTP.");
               return ResponseEntity.ok().headers(responseHeaders).body(new SuccessResponseBody(false));
           }
       }
       catch(Exception e){
           responseHeaders.set("error", "Something went wrong, please try again later.");
           return ResponseEntity.ok().headers(responseHeaders).body(new SuccessResponseBody(false));
       }

    }

}
