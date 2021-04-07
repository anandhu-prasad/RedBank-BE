package com.javainuse.controller;

import com.javainuse.config.JwtTokenUtil;
import com.javainuse.requests.BuyBlood_ReqBody;
import com.javainuse.requests.ConfirmBuy_ReqBody;
import com.javainuse.responses.BuyBlood_RespBody;
import com.javainuse.responses.SuccessResponseBody;
import com.javainuse.service.BuyBloodDAO;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/buyblood")
public class BuyBloodController {

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    public BuyBloodDAO buyBloodDAO;

    //This Post Mapping is for finding the Blood banks as per the details filled by the user
    @PostMapping("/findbb")
    public ResponseEntity<List<BuyBlood_RespBody>> getBloodBanksList(@RequestBody BuyBlood_ReqBody data , @RequestHeader("Authorization") String userToken) {
        Claims claims = jwtTokenUtil.getAllClaimsFromToken(userToken.substring(7));
        String userId = claims.get("userId").toString();
        Integer userType = Integer.parseInt(claims.get("userType").toString());

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("success", "true");


        return ResponseEntity.ok().headers(responseHeaders).body(buyBloodDAO.findBloodBanks(data,userId));
    }
    //This Post Mapping is used to confirm the buying of blood by the user from the BloodBanks
   @PostMapping("/confirmbuy")
    public ResponseEntity<?> confirmbuy(@RequestBody ConfirmBuy_ReqBody data, @RequestHeader("Authorization") String userToken){

       Claims claims = jwtTokenUtil.getAllClaimsFromToken(userToken.substring(7));
       String userId = claims.get("userId").toString();
       Integer userType = Integer.parseInt(claims.get("userType").toString());


       return  buyBloodDAO.submitSale(userId,data,userType);
   }
}
