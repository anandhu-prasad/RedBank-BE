package com.javainuse.controller;


import com.javainuse.models.ContactUs;
import com.javainuse.responses.SuccessResponseBody;
import com.javainuse.service.ContactUsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( path="/contactus" )
public class ContactUsController {

    @Autowired
    ContactUsDAO contactUsDAO;

    @PostMapping("/addMessage")
    public ResponseEntity<SuccessResponseBody> addMessage(@RequestBody ContactUs message){
        String userId="bob";
        return contactUsDAO.save(message);
    }

}
