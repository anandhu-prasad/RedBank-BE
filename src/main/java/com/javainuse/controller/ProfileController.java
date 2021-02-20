package com.javainuse.controller;

import com.javainuse.config.JwtTokenUtil;
import com.javainuse.model.ProfileBb;
import com.javainuse.model.ProfileHos;
import com.javainuse.model.ProfileInd;
import com.javainuse.repositories.ProfileBbRepository;
import com.javainuse.repositories.ProfileHosRepository;
import com.javainuse.repositories.ProfileIndRepository;
import com.javainuse.requests.DonorStatusRequestBody;
import com.javainuse.requests.ProfileBB_HosData;
import com.javainuse.requests.ProfileIndData;
import com.javainuse.responses.SuccessResponseBody;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    ProfileIndRepository profileIndRepository;

    @Autowired
    ProfileHosRepository profileHosRepository;

    @Autowired
    ProfileBbRepository profileBbRepository;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    //? SETTING NEW DONOR STATUS.
    //! TESTED
    @PutMapping("/donorstatus")
    public ResponseEntity<SuccessResponseBody> updateDonorStatus(@RequestBody DonorStatusRequestBody donorStatusRequestBody, @RequestHeader ("Authorization") String userToken){

        try{
            //! HERE, YOU NEED TO EXTRACT THE USER ID FROM TOKEN, SINCE TOKEN IS NOT AVAILABLE, DATA IS HARDCODED - DONE.
            Claims claims = jwtTokenUtil.getAllClaimsFromToken(userToken.substring(7));

            String userId = claims.get("userId").toString();
            int userType = Integer.parseInt(claims.get("userType").toString());

            ProfileInd profileInd = profileIndRepository.findByUserId(userId);
            profileInd.setDonorStatus(donorStatusRequestBody.getDonorStatus());
            profileIndRepository.save(profileInd);
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

            ProfileInd match = profileIndRepository.findByUserId(userId);

            //? SETTING NEW VALUES OF THOSE FIELDS THAT ARE EDITABLE.
            match.setAddress(profile.getAddress());
            match.setDistrict(profile.getDistrict());
            match.setState(profile.getState());
            match.setPincode(profile.getPincode());
            match.setPhone(profile.getPhone());
            match.setBloodGroup(profile.getBloodGroup());

            profileIndRepository.save(match);

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

            ProfileHos match = profileHosRepository.findByUserId(userId);

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

            profileHosRepository.save(match);
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

            ProfileBb match = profileBbRepository.findByUserId(userId);

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


            profileBbRepository.save(match);
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
//        return profileIndRepository.save(profile);
//    }
//
//    @PostMapping("/addnewprofilehos")
//    public ProfileHos addNewProfileHos(@RequestBody ProfileHos profile){
//        return profileHosRepository.save(profile);
//    }
//
//    @PostMapping("/addnewprofilebb")
//    public ProfileBb addNewProfileBb(@RequestBody ProfileBb profile){
//        return profileBbRepository.save(profile);
//    }
//
//    @GetMapping("/recieveprofile")
//    public List<ProfileInd> extractProfileDetails(){
//        System.out.println("getting profile data now!");
//        return profileIndRepository.findAll();
//    }
//
//    //!HARDCODED HERE, SET TO ACTUAL
//    @GetMapping("/userprofile")
//    public ProfileInd extractProfile(){
//        return profileIndRepository.findByUserId("IND1234");
//    }

    //?//////////////////////////////////////////////////////////////////////////////////////////////////////////

}
