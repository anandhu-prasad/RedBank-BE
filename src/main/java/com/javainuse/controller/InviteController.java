package com.javainuse.controller;

import com.javainuse.config.JwtTokenUtil;
import com.javainuse.models.*;
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

//    ? WE HAVE TO CREATE A SEPARATE CLASS FOR INVITES AND GET THE LIST OF DRIVES AND DONTIONS WHICH MEANS MAJOR CHANGES IN FRONT END CODE
    //? WE HAVE TO ADD  NEW PROP IN THE OBJECT THAT WILL REPRESENT THE TYPE OF EVENT, DONATION OR DRIVE.
    //? BUT IN CASE OF DRIVES, WE HAVE MORE FIELDS, WE MIGHT HAVE TO SEND EMPTY DATA THERE, AND CONDITIONALLY RENDER IT AT FRONT END.

    @GetMapping("/fetchinvites")
    public ResponseEntity<List<?>> fetchInvites(@RequestHeader ("Authorization") String userToken){

        try{
            //! HARDCODED DATA, USER ID TO BE EXTRACTED FROM THE TOKEN - DONE.
            Claims claims = jwtTokenUtil.getAllClaimsFromToken(userToken.substring(7));
            String userId = claims.get("userId").toString();

            System.out.println("1");

            List <Object> responseList = new ArrayList<>();
            Timestamp currTime = new Timestamp(System.currentTimeMillis());

            //? GETTING THE LIST OF DONATION REQUESTS FROM drive_invited_donors TABLE.

            List <DonationInvitedDonors> donationInvitedDonors = donationInvitedDonorsRepo.findByUserId(userId);

            List <DriveInvitedDonors> driveInvitedDonors = driveInvitedDonorsRepo.findByUserId(userId);

            for (DonationInvitedDonors donationInvitedDonor : donationInvitedDonors) {
                //! HERE, I NEED TO ADD THE NEW DATA SORTED ON THE BASIS OF CREATION DATE OF THE INVITE

                System.out.println("2");

                if (donationInvitedDonor.getAcceptance() == 2) {

                String donationId = donationInvitedDonor.getDonationId();
                DonationRequest donationRequest = donationRequestRepo.findByDonationId(donationId);

                if(donationRequest.getStatus()){

                    String recipientName;
                    String recipientType;
                    String recipientEmail;
                    String recipientContact;

                    if(donationRequest.getUserId().substring(0, 3).equals("HOS")){
                        recipientType = "Hospital";
                        ProfileHos profileHos = profileHosRepo.findByUserId(donationRequest.getUserId());
                        recipientName = profileHos.getName();
                        recipientEmail = profileHos.getEmail();
                        recipientContact = profileHos.getPhone1();
                    }
                    else if(donationRequest.getUserId().substring(0, 3).equals("BOB")){
                        recipientType = "Blood Bank";
                        ProfileBb profileBb = profileBbRepo.findByUserId(donationRequest.getUserId());
                        recipientName = profileBb.getName();
                        recipientEmail = profileBb.getEmail();
                        recipientContact = profileBb.getPhone1();
                    }
                    else{
                        recipientType = "Individual";
                        ProfileInd profileInd = profileIndRepo.findByUserId(donationRequest.getUserId());
                        recipientName = profileInd.getName();
                        recipientEmail = profileInd.getEmail();
                        recipientContact = profileInd.getPhone();
                    }


                    InviteDonationResponseBody inviteDonationResponseBody = new InviteDonationResponseBody(donationRequest.getRequestTime(), donationId, donationInvitedDonor.getAcceptance(), recipientName, recipientType, recipientEmail, recipientContact, donationRequest.getAddress());
                    responseList.add(inviteDonationResponseBody);

                }}
            }


            for (DriveInvitedDonors driveInvitedDonor : driveInvitedDonors) {
                //! HERE, I NEED TO ADD THE NEW DATA SORTED ON THE BASIS OF CREATION DATE OF THE INVITE

                System.out.println("3");

                if (driveInvitedDonor.getAcceptance() == 2) {
                String driveId = driveInvitedDonor.getDriveId();
                Drives drive = drivesRepo.findByDriveId(driveId);

                if(drive.getStatus()){

                    String recipientName;
                    String recipientType;
                    String recipientEmail;
                    String recipientContact;

                    if(drive.getUserId().substring(0, 3).equals("HOS")){
                        recipientType = "Hospital";
                        ProfileHos profileHos = profileHosRepo.findByUserId(drive.getUserId());
                        recipientName = profileHos.getName();
                        recipientEmail = profileHos.getEmail();
                        recipientContact = profileHos.getPhone1();
                    }
                    else{
                        recipientType = "Blood Bank";
                        ProfileBb profileBb = profileBbRepo.findByUserId(drive.getUserId());
                        recipientName = profileBb.getName();
                        recipientEmail = profileBb.getEmail();
                        recipientContact = profileBb.getPhone1();
                    }
                System.out.print(drive.getStartTimestamp());
                System.out.println(currTime);


//                 && new Timestamp().compareTo(currTime) > 0
                //TODO CONDITION BASED ON DATE.


                    System.out.println("6");

                    InviteDriveResponseBody inviteDriveResponseBody = new InviteDriveResponseBody(drive.getRequestTime(), driveId, driveInvitedDonor.getAcceptance(), recipientName, recipientType, recipientEmail, recipientContact, drive.getAddress(), drive.getDistrict(), drive.getState(), drive.getPincode(), drive.getStartTimestamp(), drive.getEndTimestamp(), drive.getMessage());
                    responseList.add(inviteDriveResponseBody);
                }
            }
            }

            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("success", "true");
            System.out.println("Okayy");
            System.out.println(responseList);
            return ResponseEntity.ok().headers(responseHeaders).body(responseList);
        }

        catch(Exception e){
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("error", "Something went wrong, please try again later.");
            return ResponseEntity.badRequest().headers(responseHeaders).build();

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

                DriveInvitedDonors driveInvitedDonors = driveInvitedDonorsRepo.findByDriveIdAndUserId(inviteRequestBody.getEventId(), userId);

                driveInvitedDonors.setAcceptance(inviteRequestBody.getAcceptance());
                driveInvitedDonors.setResponseTimeStamp(new Timestamp(System.currentTimeMillis()));
                driveInvitedDonors.setRejection_message(inviteRequestBody.getRejectionMessage());

                driveInvitedDonorsRepo.save(driveInvitedDonors);

                //? ALSO, TO SET THE NOTIFICATION, WE HAVE,

                //?1.  GET THE NAME OF THE DONOR BY FIRST FINDING THE DONATION REQUEST/ DRIVE ROW IN THE TABLE, GET THE ORGANIZER'S USER ID,
                //? THEN SET A NOTIFICATION FOR THAT ID.
                if(inviteRequestBody.getAcceptance() == 1){
                String organizerId = drivesRepo.findByDriveId(inviteRequestBody.getEventId()).getUserId();

                String donorName = profileIndRepo.findByUserId(userId).getName();

                Notification notification = new Notification(organizerId, "New donor!", donorName + " has accepted your drive invite.", new Timestamp(System.currentTimeMillis()));
                notificationRepo.save(notification);}

            }
            else{

                DonationInvitedDonors donationInvitedDonors = donationInvitedDonorsRepo.findByDonationIdAndUserId(inviteRequestBody.getEventId(), userId);

                donationInvitedDonors.setAcceptance(inviteRequestBody.getAcceptance());
                donationInvitedDonors.setResponseTimeStamp(new Timestamp(System.currentTimeMillis()));
                donationInvitedDonors.setRejection_message(inviteRequestBody.getRejectionMessage());

                donationInvitedDonorsRepo.save(donationInvitedDonors);

                //? ALSO, TO SET THE NOTIFICATION, WE HAVE,

                //?1.  GET THE NAME OF THE DONOR BY FIRST FINDING THE DONATION REQUEST/ DRIVE ROW IN THE TABLE, GET THE ORGANIZER'S USER ID,
                //? THEN SET A NOTIFICATION FOR THAT ID.
                if(inviteRequestBody.getAcceptance() == 1){
                String organizerId = donationRequestRepo.findByDonationId(inviteRequestBody.getEventId()).getUserId();

                String donorName = profileIndRepo.findByUserId(userId).getName();

                Notification notification = new Notification(organizerId, "New donor!", donorName + " has accepted your donation request.", new Timestamp(System.currentTimeMillis()));
                notificationRepo.save(notification);}

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
