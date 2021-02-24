package com.javainuse.controller;

import com.javainuse.config.JwtTokenUtil;
import com.javainuse.models.*;
import com.javainuse.repositories.NotificationRepo;
import com.javainuse.responses.ProfileDataBb_Hos;
import com.javainuse.responses.ProfileDataInd;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
//@RequestMapping("/notifications")
public class NotificationsController {

    @Autowired
    JwtTokenUtil jwtTokenUtil;
    @Autowired
    NotificationRepo notificationRepo;

    @GetMapping("/notifications")
    public ResponseEntity<List<Notification>> getProfileDetails(@RequestHeader("Authorization") String userToken){
        try{
            Claims claims = jwtTokenUtil.getAllClaimsFromToken(userToken.substring(7));
            String userId = claims.get("userId").toString();
            Integer userType = Integer.parseInt(claims.get("userType").toString());

            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("success", "true");

            List<Notification> notifications = notificationRepo.findByUserId(userId);
            for( Notification note : notificationRepo.findByUserId(userId)){
                note.setStatus(true);
                notificationRepo.save(note);
            }
            return ResponseEntity.ok().headers(responseHeaders).body(notifications);
        }
        catch(Exception e){
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("error", "Error fetching notifications. Please try again later.");
            return ResponseEntity.notFound().headers(responseHeaders).build();
        }

    }
}
