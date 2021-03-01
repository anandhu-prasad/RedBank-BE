package com.javainuse.controller;


import com.javainuse.config.JwtTokenUtil;
import com.javainuse.requests.ConductADrive_ReqBody;
import com.javainuse.responses.SuccessResponseBody;
import com.javainuse.service.ConductADriveDAO;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(path = "/conductadrive")
public class ConductADriveController {

    @Autowired
    public ConductADriveDAO conductadrive;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @PostMapping("/savedrivedetails")
    public ResponseEntity<SuccessResponseBody> saveDriveDetails (@RequestBody ConductADrive_ReqBody data, @RequestHeader("Authorization") String userToken){
        // get the user_id from the token and then -> data.setUserId( user_id )

        Claims claims = jwtTokenUtil.getAllClaimsFromToken(userToken.substring(7));
        String userId = claims.get("userId").toString();
        Integer userType = Integer.parseInt(claims.get("userType").toString());

        return conductadrive.saveDriveDetails(data, userId, userType);
    }
}
