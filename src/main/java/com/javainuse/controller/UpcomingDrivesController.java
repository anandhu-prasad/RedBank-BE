package com.javainuse.controller;


import com.javainuse.config.JwtTokenUtil;
import com.javainuse.requests.RegisterForDriveReqBody;
import com.javainuse.requests.UpcomingDrives_ReqBody;
import com.javainuse.responses.SuccessResponseBody;
import com.javainuse.responses.UpcomingDrives_RespBody;
import com.javainuse.service.UpcomingDrivesDAO;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/upcomingdrives")
public class UpcomingDrivesController {

    @Autowired
    UpcomingDrivesDAO upcomingDrivesDAO;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @PostMapping("/fetchdriveslist")
    public ResponseEntity<List<UpcomingDrives_RespBody>> getDrivesList(@RequestBody UpcomingDrives_ReqBody data){
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("success", "true");
        return ResponseEntity.ok().headers(responseHeaders).body(upcomingDrivesDAO.getDrives(data));
    }

    @PostMapping("/registerfordrive")
    public ResponseEntity<SuccessResponseBody> registerForDrive(@RequestBody RegisterForDriveReqBody registerForDriveReqBody, @RequestHeader ("Authorization") String userToken){
        try{
            Claims claims = jwtTokenUtil.getAllClaimsFromToken(userToken.substring(7));
            String userId = claims.get("userId").toString();
            int userType = Integer.parseInt(claims.get("userType").toString());

            return upcomingDrivesDAO.registerForDrive(userId, registerForDriveReqBody.getDriveId());

        }
        catch(Exception e){
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("error", "unable to register for this drive right now, please try later");
            return ResponseEntity.badRequest().headers(responseHeaders).build();
        }
    }

}
