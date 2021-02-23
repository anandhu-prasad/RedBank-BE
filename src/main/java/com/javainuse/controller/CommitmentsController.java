package com.javainuse.controller;



import com.javainuse.config.JwtTokenUtil;
import com.javainuse.service.CommitmentsDAO;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommitmentsController {

    @Autowired
    CommitmentsDAO commitmentsDAO;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    ///unique key for each child.

    @GetMapping("/commitment")
    public ResponseEntity<List<?>> getcommitmentdetails(@RequestHeader("Authorization") String userToken)
    {
        Claims claims = jwtTokenUtil.getAllClaimsFromToken(userToken.substring(7));
        String userId = claims.get("userId").toString();
        Integer userType = Integer.parseInt(claims.get("userType").toString());

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("success", "true");
        return ResponseEntity.ok().headers(responseHeaders).body(commitmentsDAO.getDetails(userId));
    }

}
