package com.javainuse.controller;

import com.javainuse.config.JwtTokenUtil;
import com.javainuse.models.ProfileBb;
import com.javainuse.models.ProfileHos;
import com.javainuse.models.ProfileInd;
import com.javainuse.repositories.ProfileBbRepo;
import com.javainuse.repositories.ProfileHosRepo;
import com.javainuse.repositories.ProfileIndRepo;
import com.javainuse.requests.*;
import com.javainuse.responses.ProfileIndividualData;
import com.javainuse.responses.ProfileDataBb_Hos;
import com.javainuse.responses.ProfileDataInd;
import com.javainuse.responses.SuccessResponseBody;
import com.javainuse.service.ChangePasswordDAO;
import com.javainuse.service.Verify_ChangePasswordDAO;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    ProfileIndRepo profileIndRepo;

    @Autowired
    ProfileHosRepo profileHosRepo;

    @Autowired
    ProfileBbRepo profileBbRepo;

    @Autowired
    JwtTokenUtil jwtTokenUtil;


    @Autowired
    Verify_ChangePasswordDAO verifyChangePasswordDAO;


    @GetMapping("/fetchuserprofile")
    public Object getProfileDetails(@RequestHeader ("Authorization") String userToken){
        Claims claims = jwtTokenUtil.getAllClaimsFromToken(userToken.substring(7));
        String userId = claims.get("userId").toString();
        Integer userType = Integer.parseInt(claims.get("userType").toString());



        if(userType == 1){
            ProfileInd obj = profileIndRepo.findByUserId(userId);
            ProfileDataInd obj1 = new ProfileDataInd(obj.getName(), obj.getUserId(), obj.getDonorStatus());
            return  obj1;
        }
        else if(userType == 3){
            ProfileBb obj = profileBbRepo.findByUserId(userId);
            ProfileDataBb_Hos obj1 = new ProfileDataBb_Hos(obj.getName(), obj.getUserId());
            return  obj1;
        }else{
            ProfileHos obj = profileHosRepo.findByUserId(userId);
            ProfileDataBb_Hos obj1 = new ProfileDataBb_Hos(obj.getName(), obj.getUserId());
            return  obj1;
        }
    }

    @GetMapping("/fetchuserdata")
    public Object getProfileDataDetails(@RequestHeader ("Authorization") String userToken){
        Claims claims = jwtTokenUtil.getAllClaimsFromToken(userToken.substring(7));
        String userId = claims.get("userId").toString();
        Integer userType = Integer.parseInt(claims.get("userType").toString());



        if(userType == 1){
            ProfileInd obj = profileIndRepo.findByUserId(userId);
            ProfileIndividualData obj1 = new ProfileIndividualData(obj.getBloodGroup(), obj.getEmail(), obj.getDob(), obj.getPhone(), obj.getAddress(), obj.getState(), obj.getDistrict(),obj.getPincode(),obj.getRegistration_date(),obj.getLast_donation_date());
            return  obj1;
        }
        else if(userType == 3){
            ProfileBb obj = profileBbRepo.findByUserId(userId);
            ProfileBB_HosData obj1 = new ProfileBB_HosData(obj.getEmail(), obj.getLicense_number(), obj.getPhone1(),obj.getPhone2(), obj.getPhone3(),obj.getPhone4(),obj.getPhone5(),obj.getAddress(),obj.getState(),obj.getDistrict(),obj.getPincode(),obj.getRegistration_date());
            return  obj1;
        }else{
            ProfileHos obj = profileHosRepo.findByUserId(userId);
            ProfileBB_HosData obj1 = new ProfileBB_HosData(obj.getEmail(),obj.getLicense_number(),obj.getPhone1(),
                    obj.getPhone2(),obj.getPhone3(),obj.getPhone4(),obj.getPhone5(), obj.getAddress(),obj.getState(),
                    obj.getDistrict(), obj.getPincode(),obj.getRegistration_date());
            return  obj1;
        }
    }

//    Password
    @PostMapping("/verifycurrentPassword")
    public ResponseEntity<SuccessResponseBody> verifyPass(@RequestHeader ("Authorization") String userToken ,@RequestBody CurrentPassword currentPassword ) {
        Claims claims = jwtTokenUtil.getAllClaimsFromToken(userToken.substring(7));
        String userId = claims.get("userId").toString();
        Integer userType = Integer.parseInt(claims.get("userType").toString());

        if(userType == 1){
            return verifyChangePasswordDAO.verifyIndPassword(currentPassword.getCurrentPassword(), userId);
        }else if(userType == 3){
            return verifyChangePasswordDAO.verifyBbPassword(currentPassword.getCurrentPassword(), userId);
        }else{
            return verifyChangePasswordDAO.verifyHosPassword(currentPassword.getCurrentPassword(), userId);
        }
    }

    @PutMapping("/changePassword")
    public ResponseEntity<SuccessResponseBody> changePass(@RequestHeader ("Authorization") String userToken ,@RequestBody NewPassword newPassword ) {
        Claims claims = jwtTokenUtil.getAllClaimsFromToken(userToken.substring(7));
        String userId = claims.get("userId").toString();
        Integer userType = Integer.parseInt(claims.get("userType").toString());

        if (userType == 1) {
            return verifyChangePasswordDAO.changeIndPassword(newPassword.getNewPassword(), userId);
        } else if (userType == 3) {
            return verifyChangePasswordDAO.changeBbPassword(newPassword.getNewPassword(), userId);
        } else {
            return verifyChangePasswordDAO.changeHosPassword(newPassword.getNewPassword(), userId);
        }
    }
    //? SETTING NEW DONOR STATUS.
    //! TESTED
    @PutMapping("/donorstatus")
    public ResponseEntity<SuccessResponseBody> updateDonorStatus(@RequestBody DonorStatusRequestBody donorStatusRequestBody, @RequestHeader ("Authorization") String userToken){

        try{
            //! HERE, YOU NEED TO EXTRACT THE USER ID FROM TOKEN, SINCE TOKEN IS NOT AVAILABLE, DATA IS HARDCODED - DONE.
            Claims claims = jwtTokenUtil.getAllClaimsFromToken(userToken.substring(7));

            String userId = claims.get("userId").toString();
            int userType = Integer.parseInt(claims.get("userType").toString());

            ProfileInd profileInd = profileIndRepo.findByUserId(userId);
            profileInd.setDonorStatus(donorStatusRequestBody.getDonorStatus());
            profileIndRepo.save(profileInd);
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("success", "true");
            System.out.println("Donor status changed to " + donorStatusRequestBody.getDonorStatus());
            return ResponseEntity.ok().headers(responseHeaders).body(new SuccessResponseBody(true));
        }
        catch(Exception e){
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("error", "cannot set donor status right now, please try again later.");
            return ResponseEntity.notFound().headers(responseHeaders).build();
        }
    }


    //? UPDATING INDIVIDUAL PROFILE
    //! TESTED
    @PutMapping("/updateindprofile")
    public ResponseEntity<SuccessResponseBody> updateIndProfile(@RequestBody ProfileIndData profile, @RequestHeader ("Authorization") String userToken){
        try{

            Claims claims = jwtTokenUtil.getAllClaimsFromToken(userToken.substring(7));

            String userId = claims.get("userId").toString();
//            int userType = Integer.parseInt(claims.get("userType").toString());

            ProfileInd match = profileIndRepo.findByUserId(userId);

            //? SETTING NEW VALUES OF THOSE FIELDS THAT ARE EDITABLE.
            match.setAddress(profile.getAddress());
            match.setDistrict(profile.getDistrict());
            match.setState(profile.getState());
            match.setPincode(profile.getPincode());
            match.setPhone(profile.getPhone());
            match.setBloodGroup(profile.getBloodGroup());

            profileIndRepo.save(match);

            //! SEND THE NEWLY UPDATED PROFILE DETAILS AS RESPONSE BODY IF REQUIRED.

            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("success", "true");
            return ResponseEntity.ok().headers(responseHeaders).body(new SuccessResponseBody((true)));
        }
        catch(Exception e){
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("error", "Cannot update your profile at the moment, please try again.");
            return ResponseEntity.notFound().headers(responseHeaders).build();
        }
    }


    //? UPDATING HOSPITAL PROFILE
    //!TESTED
    @PutMapping("/updatehosprofile")
    public ResponseEntity<SuccessResponseBody> updateHosProfile(@RequestBody ProfileBB_HosData profile, @RequestHeader ("Authorization") String userToken){
        try{
            Claims claims = jwtTokenUtil.getAllClaimsFromToken(userToken.substring(7));

            String userId = claims.get("userId").toString();
//            int userType = Integer.parseInt(claims.get("userType").toString());

            ProfileHos match = profileHosRepo.findByUserId(userId);

            //? SETTING NEW VALUES OF THOSE FIELDS THAT ARE EDITABLE.
            match.setAddress(profile.getAddress());
            match.setDistrict(profile.getDistrict());
            match.setState(profile.getState());
            match.setPincode(profile.getPincode());

            if(profile.getPhone().size() >= 1){
                match.setPhone1(profile.getPhone().get(0));
            }
            if(profile.getPhone().size() >= 2){
                match.setPhone2(profile.getPhone().get(1));
            }
            if(profile.getPhone().size() >= 3){
                match.setPhone3(profile.getPhone().get(2));
            }
            if(profile.getPhone().size() >= 4){
                match.setPhone4(profile.getPhone().get(3));
            }
            if(profile.getPhone().size() >= 5){
                match.setPhone5(profile.getPhone().get(4));
            }


            //TODO ADD ALL THE GETTERS AND SETTERS IF THIS DOESN'T WORK.

            profileHosRepo.save(match);
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("success", "true");
            return ResponseEntity.ok().headers(responseHeaders).body(new SuccessResponseBody((true)));
        }
        catch(Exception e){
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("error", "Cannot update your profile at the moment, please try again.");
            return ResponseEntity.notFound().headers(responseHeaders).build();
        }
    }

    //? UPDATING BLOOD BANK PROFILE
    //!TESTED
    @PutMapping("/updatebbprofile")
    public ResponseEntity<SuccessResponseBody> updateBbProfile(@RequestBody ProfileBB_HosData profile, @RequestHeader ("Authorization") String userToken){
        try{

            Claims claims = jwtTokenUtil.getAllClaimsFromToken(userToken.substring(7));

            String userId = claims.get("userId").toString();
//          int userType = Integer.parseInt(claims.get("userType").toString());

            ProfileBb match = profileBbRepo.findByUserId(userId);

            //? SETTING NEW VALUES OF THOSE FIELDS THAT ARE EDITABLE.
            match.setAddress(profile.getAddress());
            match.setDistrict(profile.getDistrict());
            match.setState(profile.getState());
            match.setPincode(profile.getPincode());

            if(profile.getPhone().size() >= 1){
                match.setPhone1(profile.getPhone().get(0));
            }
            if(profile.getPhone().size() >= 2){
                match.setPhone2(profile.getPhone().get(1));
            }
            if(profile.getPhone().size() >= 3){
                match.setPhone3(profile.getPhone().get(2));
            }
            if(profile.getPhone().size() >= 4){
                match.setPhone4(profile.getPhone().get(3));
            }
            if(profile.getPhone().size() >= 5){
                match.setPhone5(profile.getPhone().get(4));
            }


            profileBbRepo.save(match);
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("success", "true");
            return ResponseEntity.ok().headers(responseHeaders).body(new SuccessResponseBody((true)));

        }
        catch(Exception e){
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("error", "Cannot update your profile at the moment, please try again.");
            return ResponseEntity.notFound().headers(responseHeaders).build();
        }
    }



    //? FOR TESTING ONLY ///////////////////////////////////////////////////////////////////////////////////////////////////////////

//
//    @PostMapping("/addnewprofileind")
//    public ProfileInd addNewProfileInd(@RequestBody ProfileInd profile){
//        return profileIndRepo.save(profile);
//    }
//
//    @PostMapping("/addnewprofilehos")
//    public ProfileHos addNewProfileHos(@RequestBody ProfileHos profile){
//        return profileHosRepo.save(profile);
//    }
//
//    @PostMapping("/addnewprofilebb")
//    public ProfileBb addNewProfileBb(@RequestBody ProfileBb profile){
//        return profileBbRepo.save(profile);
//    }
//
//    @GetMapping("/recieveprofile")
//    public List<ProfileInd> extractProfileDetails(){
//        System.out.println("getting profile data now!");
//        return profileIndRepo.findAll();
//    }
//
//    //!HARDCODED HERE, SET TO ACTUAL
//    @GetMapping("/userprofile")
//    public ProfileInd extractProfile(){
//        return profileIndRepo.findByUserId("IND1234");
//    }

    //?//////////////////////////////////////////////////////////////////////////////////////////////////////////

}
