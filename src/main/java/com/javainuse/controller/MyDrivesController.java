package com.javainuse.controller;

import com.javainuse.config.JwtTokenUtil;
import com.javainuse.models.DriveInvitedDonors;
import com.javainuse.models.Drives;
import com.javainuse.repositories.DriveInvitedDonorsRepo;
import com.javainuse.repositories.DrivesRepo;
import com.javainuse.requests.CancelMyDrives;
import com.javainuse.requests.DriveDonorVerification_ReqBody;
import com.javainuse.responses.DriveDonorsList_RespBody;
import com.javainuse.responses.MyDrivesData;
import com.javainuse.responses.SuccessResponseBody;
import com.javainuse.service.DrivesDAO;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MyDrivesController {

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    DrivesRepo drivesRepo;

    @Autowired
    DriveInvitedDonorsRepo driveInvitedDonorsRepo;

    @GetMapping("/mydrives")
    public ResponseEntity<?> getDriveDetails(@RequestHeader("Authorization") String userToken){
        Claims claims = jwtTokenUtil.getAllClaimsFromToken(userToken.substring(7));
        String userId = claims.get("userId").toString();

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("success", "true");
        //have to adde the arraylist of blood groups
        Drives obj = drivesRepo.findByUserId(userId);
        MyDrivesData obj1 = new MyDrivesData(obj.getDriveId(),obj.getStartTimestamp(),obj.getEndTimestamp(),obj.getAddress(),obj.getState(),obj.getDistrict(),obj.getPincode(),obj.getStatus(),obj.getaPos(),obj.getaNeg(),obj.getbPos(),obj.getbNeg()
        ,obj.getoPos(),obj.getoNeg(),obj.getAbPos(),obj.getAbNeg());
            return ResponseEntity.ok().headers(responseHeaders).body(obj1);

        }

    @PutMapping("/mydrivescancel")
    public ResponseEntity<SuccessResponseBody> mydrivecancel(@RequestBody CancelMyDrives cancelMyDrives){


        try{
            Drives drives = drivesRepo.findByDriveId(cancelMyDrives.getDriveId());
            drives.setStatus(false);

            drivesRepo.save(drives);

            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("success", "true");
            return ResponseEntity.ok().headers(responseHeaders).body(new SuccessResponseBody(true));
        }
        catch(Exception e){
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("error", "This drive cannot be cancelled right now, please try again later.");
            return ResponseEntity.notFound().headers(responseHeaders).build();
        }
    }

    @Autowired
    DrivesDAO drivesDAO;
    @GetMapping("/fetchdrivedonorlist/{driveId}")
    public ResponseEntity<List<DriveDonorsList_RespBody>> getDriveDetails(@PathVariable(value = "driveId") String driveId, @RequestHeader("Authorization") String userToken) {
        Claims claims = jwtTokenUtil.getAllClaimsFromToken(userToken.substring(7));
        String userId = claims.get("userId").toString();
        Integer userType = Integer.parseInt(claims.get("userType").toString());

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("success", "true");
        return ResponseEntity.ok().headers(responseHeaders).body(drivesDAO.getDriveDonorDetails(driveId));
    }


    @PutMapping("/drivedonorverification")
    public ResponseEntity<SuccessResponseBody> drivedonorverify(@RequestBody DriveDonorVerification_ReqBody driveDonorVerification_reqBody){


        try{
            DriveInvitedDonors driveInvitedDonors = driveInvitedDonorsRepo.findByDriveIdAndUserId(driveDonorVerification_reqBody.getDriveId(), driveDonorVerification_reqBody.getUserId());
            driveInvitedDonors.setDonation_status(true);

            driveInvitedDonorsRepo.save(driveInvitedDonors);

            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("success", "true");
            return ResponseEntity.ok().headers(responseHeaders).body(new SuccessResponseBody(true));
        }
        catch(Exception e){
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("error", "This status is not set right now, please try again later.");
            return ResponseEntity.notFound().headers(responseHeaders).build();
        }
    }

}
