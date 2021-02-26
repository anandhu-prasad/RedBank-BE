package com.javainuse.controller;

import java.util.HashMap;
import java.util.Map;

import com.javainuse.service.EmailService;
import com.javainuse.service.EmailTemplate;
import com.javainuse.service.OTPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;


@RestController
public class OTPController {


    @Autowired
    public OTPService otpService;

    @Autowired
    public EmailService emailService;

    @GetMapping("/generateOtp")
    public String generateOTP() throws MessagingException {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        int otp = otpService.generateOTP(username);
        //Generate The Template to send OTP
        EmailTemplate template = new EmailTemplate("SendOtp.html");
        Map<String,String> replacements = new HashMap<String,String>();
        //This mapping will store the username and the OTP value
        replacements.put("user", username);
        replacements.put("otpnum", String.valueOf(otp));
        String message = template.getTemplate(replacements);
        //here we have to add the email address of the receiver in 'to'
        emailService.sendOtpMessage("vipulkumar@gmail.com", "OTP -SpringBoot", message);

        return "otppage";
    }

    //To verify the OTP
    @RequestMapping(value ="/verifyotp", method = RequestMethod.GET)
    public @ResponseBody String validateOtp(@RequestParam("otpnum") int otpnum){

        final String SUCCESS = "Entered Otp is valid";
        final String FAIL = "Entered Otp is NOT valid. Please Retry!";
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        //Validate the Otp
        if(otpnum >= 0){

            int serverOtp = otpService.getOtp(username);
            if(serverOtp > 0){
                if(otpnum == serverOtp){
                    otpService.clearOTP(username);

                    return ("Enter OTP is valid");
                }
                else {
                    return FAIL;
                }
            }else {
                return FAIL;
            }
        }else {
            return FAIL;
        }
    }
}