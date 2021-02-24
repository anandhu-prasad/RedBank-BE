package com.javainuse.controller;


import com.javainuse.config.JwtTokenUtil;
import com.javainuse.requests.FindDonors_ReqBody;
import com.javainuse.requests.FindDonors_ReqBody_withSelectedDonors;
import com.javainuse.responses.FindDonors_RespBody;
import com.javainuse.responses.SuccessResponseBody;
import com.javainuse.service.FindDonorsDAO;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@CrossOrigin
@RequestMapping( path="/finddonors")
public class FindDonorsController {

    @Autowired
    FindDonorsDAO findDonorsDAO;

    @Autowired
    JwtTokenUtil jwtTokenUtil;


    @PostMapping("/donorslist")
    public ResponseEntity<List<FindDonors_RespBody>> getDonorsList(@RequestBody FindDonors_ReqBody data) {

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("success", "true");

        return ResponseEntity.ok().headers(responseHeaders).body(findDonorsDAO.getDonorsList(data));
    }

    @PostMapping("/sendnotification")
    public ResponseEntity<SuccessResponseBody> getResponse(@RequestBody FindDonors_ReqBody_withSelectedDonors data, @RequestHeader("Authorization") String userToken){
        Claims claims = jwtTokenUtil.getAllClaimsFromToken(userToken.substring(7));
        String userId = claims.get("userId").toString();
        Integer userType = Integer.parseInt(claims.get("userType").toString());

        return findDonorsDAO.getResponse(data, userId, userType);

    }
}
