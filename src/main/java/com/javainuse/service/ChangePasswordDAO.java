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
public class ChangePasswordDAO {

    @Autowired
    ProfileBbRepo profileBbRepository;
    @Autowired
    ProfileHosRepo profileHosRepository;
    @Autowired
    ProfileIndRepo profileIndRepository;
    @Autowired
    private PasswordEncoder bycrptEncoder;

    public ResponseEntity<SuccessResponseBody> changeIndPassword(String message, String userId) {
        ProfileInd obj = profileIndRepository.findByUserId(userId);
        BCryptPasswordEncoder b = new BCryptPasswordEncoder();
            obj.setPassword(bycrptEncoder.encode(message));
            profileIndRepository.save(obj);
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("success", "true");
            return ResponseEntity.ok().headers(responseHeaders).body(new SuccessResponseBody(true));
    }

    public ResponseEntity<SuccessResponseBody> changeBbPassword(String message, String userId) {
        ProfileBb obj = profileBbRepository.findByUserId(userId);
        BCryptPasswordEncoder b = new BCryptPasswordEncoder();
            obj.setPassword(bycrptEncoder.encode(message));
            profileBbRepository.save(obj);
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("success", "true");

            return ResponseEntity.ok().headers(responseHeaders).body(new SuccessResponseBody(true));
    }

    public ResponseEntity<SuccessResponseBody> changeHosPassword(String message, String userId) {
        ProfileHos obj = profileHosRepository.findByUserId(userId);
        BCryptPasswordEncoder b = new BCryptPasswordEncoder();

            obj.setPassword(bycrptEncoder.encode(message));
            profileHosRepository.save(obj);
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("success", "true");

            return ResponseEntity.ok().headers(responseHeaders).body(new SuccessResponseBody(true));

    }
}
