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
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class Verify_ChangePasswordDAO {

    @Autowired
    ProfileBbRepo profileBbRepository;
    @Autowired
    ProfileIndRepo profileIndRepository;
    @Autowired
    ProfileHosRepo profileHosRepository;
    @Autowired
    PasswordEncoder bcryptEncoder;


    public ResponseEntity<SuccessResponseBody> verifyIndPassword(String currentPassword, String userId) {
        ProfileInd obj = profileIndRepository.findByUserId(userId);
        BCryptPasswordEncoder b = new BCryptPasswordEncoder();
        if (b.matches(currentPassword, obj.getPassword())) {
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("success", "true");

            return ResponseEntity.ok().headers(responseHeaders).body(new SuccessResponseBody(true));
        }else {
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("success", "false");

            return ResponseEntity.ok().headers(responseHeaders).body(new SuccessResponseBody(false));
        }
    }

    public ResponseEntity<SuccessResponseBody> verifyBbPassword(String currentPassword, String userId) {
        ProfileBb obj = profileBbRepository.findByUserId(userId);
        BCryptPasswordEncoder b = new BCryptPasswordEncoder();
        if (b.matches(currentPassword, obj.getPassword())) {
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("success", "true");

            return ResponseEntity.ok().headers(responseHeaders).body(new SuccessResponseBody(true));
        }else {
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("success", "false");

            return ResponseEntity.ok().headers(responseHeaders).body(new SuccessResponseBody(false));
        }
    }

    public ResponseEntity<SuccessResponseBody> verifyHosPassword(String currentPassword, String userId) {
        ProfileHos obj = profileHosRepository.findByUserId(userId);
        BCryptPasswordEncoder b = new BCryptPasswordEncoder();
        if (b.matches(currentPassword, obj.getPassword())) {
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("success", "true");

            return ResponseEntity.ok().headers(responseHeaders).body(new SuccessResponseBody(true));
        }else {
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("success", "false");

            return ResponseEntity.ok().headers(responseHeaders).body(new SuccessResponseBody(false));
        }
    }

//    Change Password

    public ResponseEntity<SuccessResponseBody> changeIndPassword(String newPassword, String userId) {
        ProfileInd obj = profileIndRepository.findByUserId(userId);
        obj.setPassword(bcryptEncoder.encode(newPassword));
        profileIndRepository.save(obj);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("success", "true");
        return ResponseEntity.ok().headers(responseHeaders).body(new SuccessResponseBody(true));
    }

    public ResponseEntity<SuccessResponseBody> changeBbPassword(String newPassword, String userId) {
        ProfileBb obj = profileBbRepository.findByUserId(userId);
        obj.setPassword(bcryptEncoder.encode(newPassword));
        profileBbRepository.save(obj);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("success", "true");
        return ResponseEntity.ok().headers(responseHeaders).body(new SuccessResponseBody(true));
    }

    public ResponseEntity<SuccessResponseBody> changeHosPassword(String newPassword, String userId) {
        ProfileHos obj = profileHosRepository.findByUserId(userId);
        obj.setPassword(bcryptEncoder.encode(newPassword));
        profileHosRepository.save(obj);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("success", "true");

        return ResponseEntity.ok().headers(responseHeaders).body(new SuccessResponseBody(true));

    }

}
