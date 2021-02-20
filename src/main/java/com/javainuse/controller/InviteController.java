package com.javainuse.controller;

import com.javainuse.config.JwtTokenUtil;
import com.javainuse.model.*;
import com.javainuse.repositories.*;
import com.javainuse.requests.InviteRequestBody;
import com.javainuse.responses.InviteDonationResponseBody;
import com.javainuse.responses.InviteDriveResponseBody;
import com.javainuse.responses.SuccessResponseBody;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/invites")
public class InviteController {

    @Autowired
    DonationInvitedDonorsRepository donationInvitedDonorsRepository;

    @Autowired
    DriveInvitedDonorsRepository driveInvitedDonorsRepository;

    @Autowired
    ProfileIndRepository profileIndRepository;

    @Autowired

    ProfileHosRepository profileHosRepository;

    @Autowired

    ProfileBbRepository profileBbRepository;

    @Autowired
    NotificationRepository notificationRepository;

    @Autowired
    DrivesRepository drivesRepository;

    @Autowired
    DonationRequestRepository donationRequestRepository;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

//    ? WE HAVE TO CREATE A SEPARATE CLASS FOR INVITES AND GET THE LIST OF DRIVES AND DONTIONS WHICH MEANS MAJOR CHANGES IN FRONT END CODE
    //? WE HAVE TO ADD  NEW PROP IN THE OBJECT THAT WILL REPRESENT THE TYPE OF EVENT, DONATION OR DRIVE.
    //? BUT IN CASE OF DRIVES, WE HAVE MORE FIELDS, WE MIGHT HAVE TO SEND EMPTY DATA THERE, AND CONDITIONALLY RENDER IT AT FRONT END.

    @GetMapping("/fetchinvites")
    public ResponseEntity<List<Object>> fetchInvites(@RequestHeader ("Authorization") String userToken){

        try{
            //! HARDCODED DATA, USER ID TO BE EXTRACTED FROM THE TOKEN - DONE.
            Claims claims = jwtTokenUtil.getAllClaimsFromToken(userToken.substring(7));
            String userId = claims.get("userId").toString();


            List <Object> responseList = new ArrayList<>();
            Timestamp currTime = new Timestamp(System.currentTimeMillis());

            //? GETTING THE LIST OF DONATION REQUESTS FROM drive_invited_donors TABLE.

            List <DonationInvitedDonors> donationInvitedDonors = donationInvitedDonorsRepository.findByUserId(userId);

            List <DriveInvitedDonors> driveInvitedDonors = driveInvitedDonorsRepository.findByUserId(userId);

            for (DonationInvitedDonors donationInvitedDonor : donationInvitedDonors) {
                //! HERE, I NEED TO ADD THE NEW DATA SORTED ON THE BASIS OF CREATION DATE OF THE INVITE

                String donationId = donationInvitedDonor.getDonationId();
                DonationRequest donationRequest = donationRequestRepository.findByDonationId(donationId);

                String recipientName;
                String recipientType;
                String recipientEmail;
                int recipientContact;

                if(donationRequest.getUserId().substring(0,3) == "HOS"){
                    recipientType = "Hospital";
                    ProfileHos profileHos = profileHosRepository.findByUserId(donationRequest.getUserId());
                    recipientName = profileHos.getName();
                    recipientEmail = profileHos.getEmail();
                    recipientContact = profileHos.getPhone1();
                }
                else{
                    recipientType = "Blood Bank";
                    ProfileBb profileBb = profileBbRepository.findByUserId(donationRequest.getUserId());
                    recipientName = profileBb.getName();
                    recipientEmail = profileBb.getEmail();
                    recipientContact = profileBb.getPhone1();
                }

                if(donationRequest.isStatus()){
                    InviteDonationResponseBody inviteDonationResponseBody = new InviteDonationResponseBody(donationRequest.getRequestTime(), donationId, donationInvitedDonor.getAcceptance(), recipientName, recipientType, recipientEmail, recipientContact, donationRequest.getAddress(), donationRequest.getDistrict(), donationRequest.getState(), donationRequest.getPincode());
                    responseList.add(inviteDonationResponseBody);

                }
            }


            for (DriveInvitedDonors driveInvitedDonor : driveInvitedDonors) {
                //! HERE, I NEED TO ADD THE NEW DATA SORTED ON THE BASIS OF CREATION DATE OF THE INVITE

                String driveId = driveInvitedDonor.getDriveId();
                Drives drive = drivesRepository.findByDriveId(driveId);

                String recipientName;
                String recipientType;
                String recipientEmail;
                int recipientContact;

                if(drive.getUserId().substring(0,3) == "HOS"){
                    recipientType = "Hospital";
                    ProfileHos profileHos = profileHosRepository.findByUserId(drive.getUserId());
                    recipientName = profileHos.getName();
                    recipientEmail = profileHos.getEmail();
                    recipientContact = profileHos.getPhone1();
                }
                else{
                    recipientType = "Blood Bank";
                    ProfileBb profileBb = profileBbRepository.findByUserId(drive.getUserId());
                    recipientName = profileBb.getName();
                    recipientEmail = profileBb.getEmail();
                    recipientContact = profileBb.getPhone1();
                }


                if(drive.isStatus() && drive.getStart_timestamp().compareTo(currTime) > 0){
                    //TODO CHECK FOR TEST OUTPUT.

                    InviteDriveResponseBody inviteDriveResponseBody = new InviteDriveResponseBody(drive.getRequestTime(), driveId, driveInvitedDonor.getAcceptance(), recipientName, recipientType, recipientEmail, recipientContact, drive.getAddress(), drive.getDistrict(), drive.getState(), drive.getPincode(), drive.getStart_timestamp(), drive.getEnd_timestamp());
                    responseList.add(inviteDriveResponseBody);
                }
            }

            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("success", "true");

            return ResponseEntity.ok().headers(responseHeaders).body(responseList);
        }

        catch(Exception e){
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("error", "Something went wrong, please try again later.");
            return ResponseEntity.notFound().headers(responseHeaders).build();

        }

    }


    //? THIS API MANAGES THE RESPONSE OF INVITES SENT TO THE USER, WHETHER IT IS A SUCCESS OR A FAILURE.
    //! TESTED
    @PutMapping("/inviteresponse")
    public ResponseEntity<SuccessResponseBody> setInviteResponse(@RequestBody InviteRequestBody inviteRequestBody, @RequestHeader ("Authorization") String userToken){

        //? HERE, WE NEED TO PUT ACCEPTED INVITES, GENERATE RESPONSE TIMESTAMP AND ALSO, SET IN IN THE NOTIFICATIONS TABLE.
        try{

            Claims claims = jwtTokenUtil.getAllClaimsFromToken(userToken.substring(7));

            String userId = claims.get("userId").toString();
            int userType = Integer.parseInt(claims.get("userType").toString());

            //! IF NEEDED, THE EVENT CAN BE RECOGNIZED BY THE FIRST 3 ALPHABETS OF EVENT ID, SO THERE'S NO NEED OF EVENT TYPE, CHANGE THE CONDITION IN FUTURE IF REQUIRED.

            if(inviteRequestBody.getEventType().equals("drive")){
                //? MAKE A NEW ID OBJECT

                DriveInvitedDonors driveInvitedDonors = driveInvitedDonorsRepository.findByDriveIdAndUserId(inviteRequestBody.getEventId(), userId);

                driveInvitedDonors.setAcceptance(inviteRequestBody.getAcceptance());
                driveInvitedDonors.setResponseTimeStamp(new Timestamp(System.currentTimeMillis()));
                driveInvitedDonors.setRejection_message(inviteRequestBody.getRejectionMessage());

                driveInvitedDonorsRepository.save(driveInvitedDonors);

                //? ALSO, TO SET THE NOTIFICATION, WE HAVE,

                //?1.  GET THE NAME OF THE DONOR BY FIRST FINDING THE DONATION REQUEST/ DRIVE ROW IN THE TABLE, GET THE ORGANIZER'S USER ID,
                //? THEN SET A NOTIFICATION FOR THAT ID.
                if(inviteRequestBody.getAcceptance() == 1){
                String organizerId = drivesRepository.findByDriveId(inviteRequestBody.getEventId()).getUserId();

                String donorName = profileIndRepository.findByUserId(userId).getName();

                Notification notification = new Notification(organizerId, "New donor!", donorName + " has accepted your drive invite.", new Timestamp(System.currentTimeMillis()));
                notificationRepository.save(notification);}

            }
            else{

                DonationInvitedDonors donationInvitedDonors = donationInvitedDonorsRepository.findByDonationIdAndUserId(inviteRequestBody.getEventId(), userId);

                donationInvitedDonors.setAcceptance(inviteRequestBody.getAcceptance());
                donationInvitedDonors.setResponseTimeStamp(new Timestamp(System.currentTimeMillis()));
                donationInvitedDonors.setRejection_message(inviteRequestBody.getRejectionMessage());

                donationInvitedDonorsRepository.save(donationInvitedDonors);

                //? ALSO, TO SET THE NOTIFICATION, WE HAVE,

                //?1.  GET THE NAME OF THE DONOR BY FIRST FINDING THE DONATION REQUEST/ DRIVE ROW IN THE TABLE, GET THE ORGANIZER'S USER ID,
                //? THEN SET A NOTIFICATION FOR THAT ID.
                if(inviteRequestBody.getAcceptance() == 1){
                String organizerId = donationRequestRepository.findByDonationId(inviteRequestBody.getEventId()).getUserId();

                String donorName = profileIndRepository.findByUserId(userId).getName();

                Notification notification = new Notification(organizerId, "New donor!", donorName + " has accepted your donation request.", new Timestamp(System.currentTimeMillis()));
                notificationRepository.save(notification);}

            }

            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("success", "true");
            return ResponseEntity.ok().headers(responseHeaders).body(new SuccessResponseBody(true));
        }
        catch (Exception e){
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("error", "Something went wrong, please try again later!");
            return ResponseEntity.notFound().headers(responseHeaders).build();
        }


    }

}
