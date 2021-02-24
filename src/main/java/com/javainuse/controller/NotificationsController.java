package com.javainuse.controller;

import com.javainuse.config.JwtTokenUtil;
import com.javainuse.models.*;
import com.javainuse.repositories.NotificationRepo;
import com.javainuse.responses.ProfileDataBb_Hos;
import com.javainuse.responses.ProfileDataInd;
import com.javainuse.service.NotificationDAO;
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
    NotificationDAO notificationDAO;

    @GetMapping("/notifications")
    public ResponseEntity<List<Notification>> getNotificationList(@RequestHeader("Authorization") String userToken){

        Claims claims = jwtTokenUtil.getAllClaimsFromToken(userToken.substring(7));
        String userId = claims.get("userId").toString();

        return notificationDAO.getNotificationList(userId);

    }
}
