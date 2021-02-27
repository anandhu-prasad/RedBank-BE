package com.javainuse.controller;

import com.javainuse.config.JwtTokenUtil;
import com.javainuse.models.*;
import com.javainuse.repositories.*;
import com.javainuse.requests.*;
import com.javainuse.responses.*;
import com.javainuse.service.ChangePasswordDAO;
import com.javainuse.service.ProfileDAO;
import com.javainuse.service.Verify_ChangePasswordDAO;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/profile")
public class ProfileController {


    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    ProfileDAO profileDAO;


    @PostMapping("/setdonornotification")
    public ResponseEntity<SuccessResponseBody> setDonorStatusNotification(@RequestHeader ("Authorization") String userToken){
        Claims claims = jwtTokenUtil.getAllClaimsFromToken(userToken.substring(7));
        String userId = claims.get("userId").toString();
        int userType = Integer.parseInt(claims.get("userType").toString());

        return profileDAO.setDonorStatusNotification(userId);
    }

    @GetMapping("/fetchuserprofile")
    public ResponseEntity<?> getProfileDetails(@RequestHeader ("Authorization") String userToken){
        Claims claims = jwtTokenUtil.getAllClaimsFromToken(userToken.substring(7));
        String userId = claims.get("userId").toString();
        int userType = Integer.parseInt(claims.get("userType").toString());
        return profileDAO.getProfileDetails(userId,userType);
    }

    @GetMapping("/fetchuserdata")
    public ResponseEntity<?> getProfileDataDetails(@RequestHeader ("Authorization") String userToken){
        Claims claims = jwtTokenUtil.getAllClaimsFromToken(userToken.substring(7));
        String userId = claims.get("userId").toString();
        int userType = Integer.parseInt(claims.get("userType").toString());
        return profileDAO.getProfileDataDetails(userId, userType);
    }

    @PostMapping("/verifycurrentpassword")
    public ResponseEntity<SuccessResponseBody> verifyPass(@RequestHeader ("Authorization") String userToken ,@RequestBody CurrentPassword currentPassword ) {
        Claims claims = jwtTokenUtil.getAllClaimsFromToken(userToken.substring(7));
        String userId = claims.get("userId").toString();
        int userType = Integer.parseInt(claims.get("userType").toString());
        return profileDAO.verifyPass(currentPassword, userId, userType);
        }

    @PutMapping("/changepassword")
    public ResponseEntity<SuccessResponseBody> changePass(@RequestHeader ("Authorization") String userToken ,@RequestBody NewPassword newPassword ) {
        Claims claims = jwtTokenUtil.getAllClaimsFromToken(userToken.substring(7));
        String userId = claims.get("userId").toString();
        int userType = Integer.parseInt(claims.get("userType").toString());
        return  profileDAO.changePassword(newPassword, userId, userType);
    }

    //? SETTING NEW DONOR STATUS.
    //! TESTED
    @PutMapping("/donorstatus")
    public ResponseEntity<DonorStatusRequestBody> updateDonorStatus(@RequestBody DonorStatusRequestBody donorStatusRequestBody, @RequestHeader ("Authorization") String userToken){
        Claims claims = jwtTokenUtil.getAllClaimsFromToken(userToken.substring(7));
        String userId = claims.get("userId").toString();
        return profileDAO.updateDonorStatus(donorStatusRequestBody, userId);
    }

    //? UPDATING INDIVIDUAL PROFILE
    //! TESTED
    @PutMapping("/updateindprofile")
    public ResponseEntity<SuccessResponseBody> updateIndProfile(@RequestBody ProfileUpdateIndRequestBody profile, @RequestHeader ("Authorization") String userToken){
        Claims claims = jwtTokenUtil.getAllClaimsFromToken(userToken.substring(7));
        String userId = claims.get("userId").toString();
        return profileDAO.updateIndProfile(profile, userId);
    }

    //? UPDATING HOSPITAL PROFILE
    //!TESTED
    @PutMapping("/updatehosprofile")
    public ResponseEntity<SuccessResponseBody> updateHosProfile(@RequestBody ProfileUpdateHosBbRequestBody profile, @RequestHeader ("Authorization") String userToken){
        Claims claims = jwtTokenUtil.getAllClaimsFromToken(userToken.substring(7));
        String userId = claims.get("userId").toString();
        return profileDAO.updateHosProfile(profile, userId);
    }

    //? UPDATING BLOOD BANK PROFILE
    //!TESTED
    @PutMapping("/updatebbprofile")
    public ResponseEntity<SuccessResponseBody> updateBbProfile(@RequestBody ProfileUpdateHosBbRequestBody profile, @RequestHeader ("Authorization") String userToken){
        Claims claims = jwtTokenUtil.getAllClaimsFromToken(userToken.substring(7));
        String userId = claims.get("userId").toString();
        return profileDAO.updateBbProfile(profile, userId);
    }

}
