package com.javainuse.service;

import com.javainuse.models.ContactUs;
import com.javainuse.models.ProfileInd;
import com.javainuse.repositories.ContactUsRepo;
import com.javainuse.repositories.ProfileIndRepo;
import com.javainuse.requests.ContactUs_ReqBody;
import com.javainuse.responses.SuccessResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class ContactUsDAO {

    @Autowired
    ContactUsRepo contactusRepo;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    ProfileIndRepo profileIndRepo;

    public ResponseEntity<?> save(ContactUs_ReqBody message, String userId) {

        HttpHeaders responseHeaders = new HttpHeaders();
        ProfileInd indProfile = profileIndRepo.findByUserId(userId);

        if(userId.equals(indProfile.getUserId())){
            ContactUs obj = new ContactUs(userId, message.getSubject(), message.getMessage());
            contactusRepo.save(obj);

            responseHeaders.set("success", "true");
            return ResponseEntity.ok().headers(responseHeaders).body(new SuccessResponseBody(true));
        }
        else{
            responseHeaders.set("error", "unauthorized");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).headers(responseHeaders).build();
        }

    }
}
