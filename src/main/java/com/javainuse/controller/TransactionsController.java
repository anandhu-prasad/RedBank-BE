package com.javainuse.controller;

import com.javainuse.models.Sales;
import com.javainuse.responses.Purchases_RespBody;
import com.javainuse.responses.Sales_RespBody;
import com.javainuse.service.PurchasesDAO;
import com.javainuse.service.SalesDAO;
import io.jsonwebtoken.Claims;
import com.javainuse.config.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "/transactions")
public class TransactionsController {

        @Autowired
        PurchasesDAO purchasesDAO;

        @Autowired
        SalesDAO salesDAO;

        @Autowired
        JwtTokenUtil jwtTokenUtil;
        //This GET mapping is for getting the list of all purchases made by the user
        @GetMapping("/fetchpurchaseslist")
        public ResponseEntity<List<Purchases_RespBody>> getPurchasesList(@RequestHeader("Authorization") String userToken) {
                Claims claims = jwtTokenUtil.getAllClaimsFromToken(userToken.substring(7));
                String userId = claims.get("userId").toString();
                Integer userType = Integer.parseInt(claims.get("userType").toString());

                HttpHeaders responseHeaders = new HttpHeaders();
                responseHeaders.set("success", "true");

                return ResponseEntity.ok().headers(responseHeaders).body(purchasesDAO.getPurchasesList(userId));

        }
        //This GET mapping is specifically for the BloodBanks to get the list of all the sales made by them in the past
        @GetMapping("/fetchsaleslist")
        public ResponseEntity<List<Sales_RespBody>> getSalesList(@RequestHeader("Authorization") String userToken) {
                Claims claims = jwtTokenUtil.getAllClaimsFromToken(userToken.substring(7));
                String userId = claims.get("userId").toString();
                Integer userType = Integer.parseInt(claims.get("userType").toString());

                return salesDAO.getSalesList(userId, userType);
        }
}
