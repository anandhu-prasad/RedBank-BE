package com.javainuse.controller;

import com.javainuse.config.JwtTokenUtil;
import com.javainuse.models.*;
import com.javainuse.repositories.*;
import com.javainuse.requests.InviteRequestBody;
import com.javainuse.responses.InviteDonationResponseBody;
import com.javainuse.responses.InviteDriveResponseBody;
import com.javainuse.responses.SuccessResponseBody;
import com.javainuse.service.InvitesDAO;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/invites")
//This is the controller in which user gets the details related to invites sent by other users
public class InviteController {

    @Autowired
    DonationInvitedDonorsRepo donationInvitedDonorsRepo;

    @Autowired
    DriveInvitedDonorsRepo driveInvitedDonorsRepo;

    @Autowired
    ProfileIndRepo profileIndRepo;

    @Autowired
    ProfileHosRepo profileHosRepo;

    @Autowired
    ProfileBbRepo profileBbRepo;

    @Autowired
    NotificationRepo notificationRepo;

    @Autowired
    DrivesRepo drivesRepo;

    @Autowired
    DonationRequestRepo donationRequestRepo;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    InvitesDAO invitesDAO;

//    ? WE HAVE TO CREATE A SEPARATE CLASS FOR INVITES AND GET THE LIST OF DRIVES AND DONTIONS WHICH MEANS MAJOR CHANGES IN FRONT END CODE
    //? WE HAVE TO ADD  NEW PROP IN THE OBJECT THAT WILL REPRESENT THE TYPE OF EVENT, DONATION OR DRIVE.
    //? BUT IN CASE OF DRIVES, WE HAVE MORE FIELDS, WE MIGHT HAVE TO SEND EMPTY DATA THERE, AND CONDITIONALLY RENDER IT AT FRONT END.
    //This GET mapping is for the user to get the list of all the invites sent by the other users
    @GetMapping("/fetchinvites")
    public ResponseEntity<List<?>> fetchInvites(@RequestHeader ("Authorization") String userToken){
        Claims claims = jwtTokenUtil.getAllClaimsFromToken(userToken.substring(7));
        String userId = claims.get("userId").toString();
        int userType = Integer.parseInt(claims.get("userType").toString());

        return invitesDAO.fetchInvites(userId, userType);

    }


    //? THIS API MANAGES THE RESPONSE OF INVITES SENT TO THE USER, WHETHER IT IS A SUCCESS OR A FAILURE.
    //! TESTED
    //This PUT mapping is for updating the invite status for the invite sent by the user
    @PutMapping("/inviteresponse")
    public ResponseEntity<SuccessResponseBody> setInviteResponse(@RequestBody InviteRequestBody inviteRequestBody, @RequestHeader ("Authorization") String userToken){

        Claims claims = jwtTokenUtil.getAllClaimsFromToken(userToken.substring(7));
        String userId = claims.get("userId").toString();
        int userType = Integer.parseInt(claims.get("userType").toString());
        return invitesDAO.setInviteResponse(inviteRequestBody, userId, userType);
    }

}
