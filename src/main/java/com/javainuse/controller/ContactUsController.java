package com.javainuse.controller;

import com.javainuse.models.ContactUs;
import com.javainuse.requests.ContactUs_ReqBody;
import com.javainuse.responses.SuccessResponseBody;
import com.javainuse.service.ContactUsDAO;
import io.jsonwebtoken.Claims;
import com.javainuse.config.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/contactus")
public class ContactUsController {

    @Autowired
    ContactUsDAO contactUsDAO;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @PostMapping("/addMessage")
    public ResponseEntity<SuccessResponseBody> addMessage(@RequestBody ContactUs_ReqBody message,
            @RequestHeader("Authorization") String userToken) {
        Claims claims = jwtTokenUtil.getAllClaimsFromToken(userToken.substring(7));
        String userId = claims.get("userId").toString();
        Integer userType = Integer.parseInt(claims.get("userType").toString());

        return contactUsDAO.save(message, userId);
    }

}
