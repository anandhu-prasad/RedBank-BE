package com.javainuse.controller;


import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import com.javainuse.service.AndroidPushNotificationsService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class WebController {

    private final String TOPIC = "BOB05";

    @Autowired
    AndroidPushNotificationsService androidPushNotificationsService;

    @RequestMapping(value = "/send", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> send() throws JSONException {

        JSONObject body = new JSONObject();
        body.put("to", "/topics/" + TOPIC);
        body.put("priority", "high");

        JSONObject notification = new JSONObject();
        notification.put("title", "REDBANK test notification");
        notification.put("body", "This is a test notification.");

        JSONObject data = new JSONObject();
        data.put("Key-1", "test data 1");
        data.put("Key-2", "test data 2");

        body.put("notification", notification);
        body.put("data", data);

/**
 {
 "notification": {
 "title": "JSA Notification",
 "body": "Happy Message!"
 },
 "data": {
 "Key-1": "JSA Data 1",
 "Key-2": "JSA Data 2"
 },
 "to": "/topics/JavaSampleApproach",
 "priority": "high"
 }
 */

        HttpEntity request = new HttpEntity<>(body.toString());

        CompletableFuture pushNotification = androidPushNotificationsService.send(request);
        CompletableFuture.allOf(pushNotification).join();

        try {
            String firebaseResponse = (String) pushNotification.get();

            return new ResponseEntity<>(firebaseResponse, HttpStatus.OK);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>("Push Notification ERROR!", HttpStatus.BAD_REQUEST);
    }
}