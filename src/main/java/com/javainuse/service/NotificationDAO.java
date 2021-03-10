package com.javainuse.service;

import com.javainuse.models.Notification;
import com.javainuse.repositories.NotificationRepo;
import com.javainuse.responses.SuccessResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationDAO {

    @Autowired
    NotificationRepo notificationRepo;

    public ResponseEntity<List<Notification>> getNotificationList(String userId){

        try{
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("success", "true");
            return ResponseEntity.ok().headers(responseHeaders).body(notificationRepo.findByUserId(userId));
        }
        catch(Exception e){
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("error", "Error fetching notifications. Please try again later.");
            return ResponseEntity.notFound().headers(responseHeaders).build();
        }
    }

    public ResponseEntity<SuccessResponseBody> setNotificationStatus(String userId, int notificationId){
        try{
            HttpHeaders responseHeaders = new HttpHeaders();
            Notification notification = notificationRepo.findByNotificationId(notificationId);

            if(notification.getUserId().equals(userId)){
                notification.setStatus(true);
                notificationRepo.save(notification);
                responseHeaders.set("success", "true");
                return ResponseEntity.ok().headers(responseHeaders).body(new SuccessResponseBody(true));
            }

            else{
                responseHeaders.set("error", "You are not authorized to perform this action.");
                return ResponseEntity.badRequest().headers(responseHeaders).build();
            }
        }
        catch(Exception e){
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("error", "Error setting notification status. Please try again later.");
            return ResponseEntity.notFound().headers(responseHeaders).build();
        }
    }

}
