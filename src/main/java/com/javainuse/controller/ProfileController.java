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
@CrossOrigin
@RequestMapping("/profile")
public class ProfileController {


    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    ProfileDAO profileDAO;

    //This POST mapping is for updating the status of donor notification
    @PostMapping("/setdonornotification")
    public ResponseEntity<SuccessResponseBody> setDonorStatusNotification(@RequestHeader ("Authorization") String userToken){
        Claims claims = jwtTokenUtil.getAllClaimsFromToken(userToken.substring(7));
        String userId = claims.get("userId").toString();
        int userType = Integer.parseInt(claims.get("userType").toString());

        return profileDAO.setDonorStatusNotification(userId, userType);
    }
    //This GET mapping is to get to view all the details filled by the user during the time of registration
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
    //This POST mapping is to verify the current password so that the user can reset the password when required
    @PostMapping("/verifycurrentpassword")
    public ResponseEntity<SuccessResponseBody> verifyPass(@RequestHeader ("Authorization") String userToken ,@RequestBody CurrentPassword currentPassword ) {
        Claims claims = jwtTokenUtil.getAllClaimsFromToken(userToken.substring(7));
        String userId = claims.get("userId").toString();
        int userType = Integer.parseInt(claims.get("userType").toString());
        return profileDAO.verifyPass(currentPassword, userId, userType);
        }
    //This PUT mapping is for changing of the password of the account
    @PutMapping("/changepassword")
    public ResponseEntity<SuccessResponseBody> changePass(@RequestHeader ("Authorization") String userToken ,@RequestBody NewPassword newPassword ) {
        Claims claims = jwtTokenUtil.getAllClaimsFromToken(userToken.substring(7));
        String userId = claims.get("userId").toString();
        int userType = Integer.parseInt(claims.get("userType").toString());
        return  profileDAO.changePassword(newPassword, userId, userType);
    }

    //? SETTING NEW DONOR STATUS.
    //! TESTED
    //This PUT mapping is to update the Status of the donor to know whether he is eligible to donate or not
    @PutMapping("/donorstatus")
    public ResponseEntity<DonorStatusRequestBody> updateDonorStatus(@RequestBody DonorStatusRequestBody donorStatusRequestBody, @RequestHeader ("Authorization") String userToken){
        Claims claims = jwtTokenUtil.getAllClaimsFromToken(userToken.substring(7));
        String userId = claims.get("userId").toString();
        int userType = Integer.parseInt(claims.get("userType").toString());
        return profileDAO.updateDonorStatus(donorStatusRequestBody, userId, userType);
    }

    //? UPDATING INDIVIDUAL PROFILE
    //! TESTED
    //This PUT mapping is for updating the profile of the INDIVIDUAL if he wants to change anything
    @PutMapping("/updateindprofile")
    public ResponseEntity<SuccessResponseBody> updateIndProfile(@RequestBody ProfileUpdateIndRequestBody profile, @RequestHeader ("Authorization") String userToken){
        Claims claims = jwtTokenUtil.getAllClaimsFromToken(userToken.substring(7));
        String userId = claims.get("userId").toString();
        int userType = Integer.parseInt(claims.get("userType").toString());
        return profileDAO.updateIndProfile(profile, userId, userType);
    }

    //? UPDATING HOSPITAL PROFILE
    //!TESTED
    //This PUT mapping is for updating the profile of the HOSPITAL if he wants to change anything
    @PutMapping("/updatehosprofile")
    public ResponseEntity<SuccessResponseBody> updateHosProfile(@RequestBody ProfileUpdateHosBbRequestBody profile, @RequestHeader ("Authorization") String userToken){
        Claims claims = jwtTokenUtil.getAllClaimsFromToken(userToken.substring(7));
        String userId = claims.get("userId").toString();
        int userType = Integer.parseInt(claims.get("userType").toString());
        return profileDAO.updateHosProfile(profile, userId, userType);
    }

    //? UPDATING BLOOD BANK PROFILE
    //!TESTED
    //This PUT mapping is for updating the profile of the BLOOD BANK if he wants to change anything
    @PutMapping("/updatebbprofile")
    public ResponseEntity<SuccessResponseBody> updateBbProfile(@RequestBody ProfileUpdateHosBbRequestBody profile, @RequestHeader ("Authorization") String userToken){
        Claims claims = jwtTokenUtil.getAllClaimsFromToken(userToken.substring(7));
        String userId = claims.get("userId").toString();
        int userType = Integer.parseInt(claims.get("userType").toString());
        return profileDAO.updateBbProfile(profile, userId, userType);
    }
    //This PUT mapping is for resetting of the password when the user forgets the password
    @PutMapping("/resetpassword")
    public ResponseEntity<SuccessResponseBody> updateBbProfile(@RequestBody ResetPassword_ReqBody data){
        return profileDAO.resetPassword(data);
    }
    //This PUT mapping is for setting up the Profile avatar for the user
    @PutMapping("/setavatar")
    public ResponseEntity<SuccessResponseBody> setAvatar(@RequestBody AvatarReqBody avatarReqBody, @RequestHeader ("Authorization") String userToken){
        Claims claims = jwtTokenUtil.getAllClaimsFromToken(userToken.substring(7));
        String userId = claims.get("userId").toString();
        int userType = Integer.parseInt(claims.get("userType").toString());
        return profileDAO.updateAvatar(avatarReqBody.getAvatar(), userId, userType);
    }

}
