package com.javainuse.service;


import com.javainuse.models.ContactUs;
import com.javainuse.repositories.ContactUsRepo;
import com.javainuse.responses.SuccessResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ContactUsDAO {

    @Autowired
    ContactUsRepo contactusRepo;

    public ResponseEntity<SuccessResponseBody> save(ContactUs message){

        contactusRepo.save(message);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("success", "true");

        return ResponseEntity.ok().headers(responseHeaders).body(new SuccessResponseBody(true));
    }
}
