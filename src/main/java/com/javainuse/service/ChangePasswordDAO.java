package com.javainuse.service;

import com.javainuse.models.ProfileBb;
import com.javainuse.models.ProfileHos;
import com.javainuse.models.ProfileInd;
import com.javainuse.repositories.ProfileBbRepo;
import com.javainuse.repositories.ProfileHosRepo;
import com.javainuse.repositories.ProfileIndRepo;
import com.javainuse.responses.SuccessResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ChangePasswordDAO {

    @Autowired
    ProfileBbRepo profileBbRepository;
    @Autowired
    ProfileHosRepo profileHosRepository;
    @Autowired
    ProfileIndRepo profileIndRepository;
    @Autowired
    private PasswordEncoder bycrptEncoder;

    public ResponseEntity<?> changeIndPassword(String message, String userId) {

        HttpHeaders responseHeaders = new HttpHeaders();
        ProfileInd obj = profileIndRepository.findByUserId(userId);

        if (userId.equals(obj.getUserId())) {
            BCryptPasswordEncoder b = new BCryptPasswordEncoder();
            obj.setPassword(bycrptEncoder.encode(message));
            profileIndRepository.save(obj);

            responseHeaders.set("success", "true");
            return ResponseEntity.ok().headers(responseHeaders).body(new SuccessResponseBody(true));

        } else {
            responseHeaders.set("error", "unauthorized");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).headers(responseHeaders).build();
        }
    }

    public ResponseEntity<?> changeBbPassword(String message, String userId) {
        HttpHeaders responseHeaders = new HttpHeaders();
        ProfileBb obj = profileBbRepository.findByUserId(userId);

        if(userId.equals(obj.getUserId())){
            BCryptPasswordEncoder b = new BCryptPasswordEncoder();
            obj.setPassword(bycrptEncoder.encode(message));
            profileBbRepository.save(obj);

            responseHeaders.set("success", "true");
            return ResponseEntity.ok().headers(responseHeaders).body(new SuccessResponseBody(true));
        }else{
            responseHeaders.set("error", "unauthorized");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).headers(responseHeaders).build();
        }

    }

    public ResponseEntity<?> changeHosPassword(String message, String userId) {
        HttpHeaders responseHeaders = new HttpHeaders();
        ProfileHos obj = profileHosRepository.findByUserId(userId);

        if(userId.equals(obj.getUserId())){
            BCryptPasswordEncoder b = new BCryptPasswordEncoder();

            obj.setPassword(bycrptEncoder.encode(message));
            profileHosRepository.save(obj);

            responseHeaders.set("success", "true");

            return ResponseEntity.ok().headers(responseHeaders).body(new SuccessResponseBody(true));
        }else{
            responseHeaders.set("error", "unauthorized");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).headers(responseHeaders).build();
        }

    }
}
