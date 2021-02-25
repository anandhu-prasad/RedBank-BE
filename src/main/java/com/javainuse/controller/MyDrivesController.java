package com.javainuse.controller;


import com.javainuse.config.JwtTokenUtil;
import com.javainuse.repositories.DriveInvitedDonorsRepo;
import com.javainuse.repositories.DrivesRepo;
import com.javainuse.requests.CancelDrive;
import com.javainuse.requests.DriveDonorVerification_ReqBody;
import com.javainuse.responses.DriveDonorsList_RespBody;
import com.javainuse.responses.FetchDrivesResponseBody;
import com.javainuse.responses.SuccessResponseBody;
import com.javainuse.service.MyDrivesDAO;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/mydrives")
public class MyDrivesController {
    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    MyDrivesDAO myDrivesDAO;

    @GetMapping("/fetchdrives")
    public ResponseEntity<List<FetchDrivesResponseBody>> fetchMyDrives(@RequestHeader("Authorization") String userToken) {
        Claims claims = jwtTokenUtil.getAllClaimsFromToken(userToken.substring(7));
        String userId = claims.get("userId").toString();
        return myDrivesDAO.fetchMyDrives(userId);
    }

    @PutMapping("/canceldrive")
    public ResponseEntity<SuccessResponseBody> mydrivecancel(@RequestBody CancelDrive cancelDrive, @RequestHeader("Authorization") String userToken) {
        Claims claims = jwtTokenUtil.getAllClaimsFromToken(userToken.substring(7));
        String userId = claims.get("userId").toString();
        return myDrivesDAO.cancelDrive(cancelDrive, userId);
    }

    @PutMapping("/drivedonorverification")
    public ResponseEntity<SuccessResponseBody> driveDonorVerify(@RequestBody DriveDonorVerification_ReqBody driveDonorVerification_reqBody) {
        return myDrivesDAO.driveDonorVerify(driveDonorVerification_reqBody);
    }

    @GetMapping("/fetchdrivedonorlist/{driveId}")
    public ResponseEntity<List<DriveDonorsList_RespBody>> getDriveDetails(@PathVariable(value = "driveId") String driveId, @RequestHeader("Authorization") String userToken) {
        Claims claims = jwtTokenUtil.getAllClaimsFromToken(userToken.substring(7));
        String userId = claims.get("userId").toString();
        return myDrivesDAO.getDriveDonorList(driveId);
    }


}
