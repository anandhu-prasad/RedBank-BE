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
//This controller is for all the operations and services provided to the user for Drives
public class MyDrivesController {
    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    MyDrivesDAO myDrivesDAO;

    //This GET mapping is for getting the list of all the drives conducted by the user
    @GetMapping("/fetchdrives")
    public ResponseEntity<List<FetchDrivesResponseBody>> fetchMyDrives(@RequestHeader("Authorization") String userToken) {
        Claims claims = jwtTokenUtil.getAllClaimsFromToken(userToken.substring(7));
        String userId = claims.get("userId").toString();
        return myDrivesDAO.fetchMyDrives(userId);
    }
    //This PUT mapping is for cancel the Drive
    @PutMapping("/canceldrive")
    public ResponseEntity<SuccessResponseBody> mydrivecancel(@RequestBody CancelDrive cancelDrive, @RequestHeader("Authorization") String userToken) {
        Claims claims = jwtTokenUtil.getAllClaimsFromToken(userToken.substring(7));
        String userId = claims.get("userId").toString();
        return myDrivesDAO.cancelDrive(cancelDrive, userId);
    }
    //This PUT mapping is for updating the donor status by the user after donor has donated blood
    @PutMapping("/drivedonorverification")
    public ResponseEntity<SuccessResponseBody> driveDonorVerify(@RequestBody DriveDonorVerification_ReqBody driveDonorVerification_reqBody, @RequestHeader("Authorization") String userToken) {
        Claims claims = jwtTokenUtil.getAllClaimsFromToken(userToken.substring(7));
        String userId = claims.get("userId").toString();
        return myDrivesDAO.driveDonorVerify(driveDonorVerification_reqBody, userId);
    }
    //This GET mapping is to get all the active donors in a list based on the Drive they have accepted
    @GetMapping("/fetchdrivedonorlist/{driveId}")
    public ResponseEntity<List<DriveDonorsList_RespBody>> getDriveDetails(@PathVariable(value = "driveId") String driveId, @RequestHeader("Authorization") String userToken) {
        Claims claims = jwtTokenUtil.getAllClaimsFromToken(userToken.substring(7));
        String userId = claims.get("userId").toString();
        return myDrivesDAO.getDriveDonorList(driveId, userId);
    }


}
