package com.javainuse.controller;

import com.javainuse.models.ContactUs;
import com.javainuse.requests.ContactUs_ReqBody;
import com.javainuse.responses.SuccessResponseBody;
import com.javainuse.service.ContactUsDAO;
import io.jsonwebtoken.Claims;
import com.javainuse.config.JwtTokenUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(path = "/contactus")
//This controller is for getting connected with Admins of the RedBank
public class ContactUsController {

    @Autowired
    ContactUsDAO contactUsDAO;

    @Autowired
    JwtTokenUtil jwtTokenUtil;
    //This Post Mapping is for sending the message to the ADMINS of the RedBank app
    @ApiOperation(value= "User sends message to Developers")
    @PostMapping("/addmessage")
    public ResponseEntity<?> addMessage(@RequestBody ContactUs_ReqBody message,
            @RequestHeader("Authorization") String userToken) {
        Claims claims = jwtTokenUtil.getAllClaimsFromToken(userToken.substring(7));
        String userId = claims.get("userId").toString();
        Integer userType = Integer.parseInt(claims.get("userType").toString());

        return contactUsDAO.save(message, userId);
    }

}
