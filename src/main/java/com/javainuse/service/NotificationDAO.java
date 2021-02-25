package com.javainuse.service;

import com.javainuse.models.Notification;
import com.javainuse.repositories.NotificationRepo;
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
