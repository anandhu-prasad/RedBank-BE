package com.javainuse.controller;

import com.javainuse.config.JwtTokenUtil;
import com.javainuse.models.*;
import com.javainuse.repositories.NotificationRepo;
import com.javainuse.requests.NotificationsRequestBody;
import com.javainuse.responses.ProfileDataBb_Hos;
import com.javainuse.responses.ProfileDataInd;
import com.javainuse.responses.SuccessResponseBody;
import com.javainuse.service.NotificationDAO;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/notifications")
public class NotificationsController {

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    NotificationDAO notificationDAO;

    @GetMapping("/fetchnotifications")
    public ResponseEntity<List<Notification>> getNotificationList(@RequestHeader("Authorization") String userToken){
        Claims claims = jwtTokenUtil.getAllClaimsFromToken(userToken.substring(7));
        String userId = claims.get("userId").toString();
        return notificationDAO.getNotificationList(userId);
    }

    @PutMapping("/setstatus")
    public ResponseEntity<SuccessResponseBody> setNotificationStatus(@RequestHeader("Authorization") String userToken, @RequestBody NotificationsRequestBody notificationsRequestBody){
        Claims claims = jwtTokenUtil.getAllClaimsFromToken(userToken.substring(7));
        String userId = claims.get("userId").toString();

        return notificationDAO.setNotificationStatus(userId, notificationsRequestBody.getNotificationId());

    }

}
