package com.javainuse.controller;

import com.javainuse.models.ProfileBb;
import com.javainuse.models.ProfileHos;
import com.javainuse.models.ProfileInd;
import com.javainuse.repositories.ProfileBbRepo;
import com.javainuse.repositories.ProfileHosRepo;
import com.javainuse.repositories.ProfileIndRepo;
import com.javainuse.requests.EmailRequestBody;
import com.javainuse.service.EmailService;
import com.javainuse.service.EmailTemplate;
import com.javainuse.service.OTPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
public class OTPController {

    @Autowired
    public OTPService otpService;

    @Autowired
    ProfileBbRepo profileBbRepo;

    @Autowired
    ProfileIndRepo profileIndRepo;

    @Autowired
    ProfileHosRepo profileHosRepo;

    @Autowired
    public EmailService emailService;
// Token is not provided to the user.
    @PostMapping("/generateotp")
    public String generateOTP(@RequestBody EmailRequestBody emailRequestBody) throws MessagingException {
        String email = emailRequestBody.getEmail();
        String userId="";
        ProfileInd profileInd = profileIndRepo.findByEmail(email);
        ProfileBb profileBb = profileBbRepo.findByEmail(email);
        ProfileHos profileHos = profileHosRepo.findByEmail(email);
        if(profileInd != null ){
            userId = profileInd.getUserId();
        }else if(profileHos != null){
            userId = profileHos.getUserId();
        }else if(profileBb != null){
            userId = profileBb.getUserId();
        }else{
//            return Recovery Email is not registered.
        }
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        String username = auth.getName();
        int otp = otpService.generateOTP(userId);
        //Generate The Template to send OTP
        EmailTemplate template = new EmailTemplate("SendOtp.html");
        Map<String,String> replacements = new HashMap<String,String>();
        //This mapping will store the username and the OTP value
        replacements.put("userId", userId);
        replacements.put("otpnum", String.valueOf(otp));
        String message = template.getTemplate(replacements);
        //here we have to add the email address of the receiver in 'to'
        emailService.sendOtpMessage(email, "DONTREPLY-redbank", message);

        return "otppage";
    }

    //To verify the OTP
    @RequestMapping(value ="/verifyotp", method = RequestMethod.GET)
    public @ResponseBody
    String validateOtp(@RequestParam("otpnum") int otpnum){

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
