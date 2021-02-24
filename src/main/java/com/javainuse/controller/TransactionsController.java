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
@RequestMapping( path="/transactions" )
public class TransactionsController {

    @Autowired
    PurchasesDAO purchasesDAO;

    @Autowired
    SalesDAO salesDAO;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @GetMapping("/fetchpurchaseslist")
    public ResponseEntity<List<Purchases_RespBody>> getPurchasesList(@RequestHeader("Authorization") String userToken) {
        Claims claims = jwtTokenUtil.getAllClaimsFromToken(userToken.substring(7));
        String userId = claims.get("userId").toString();
        Integer userType = Integer.parseInt(claims.get("userType").toString());

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("success", "true");
        System.out.println(purchasesDAO.getPurchasesList(userId));
        return ResponseEntity.ok().headers(responseHeaders).body(purchasesDAO.getPurchasesList(userId));
    }

    @GetMapping("/fetchsaleslist")
    public ResponseEntity<List<Sales_RespBody>> getSalesList(@RequestHeader ("Authorization") String userToken) {
        Claims claims = jwtTokenUtil.getAllClaimsFromToken(userToken.substring(7));
        String userId = claims.get("userId").toString();
        Integer userType = Integer.parseInt(claims.get("userType").toString());

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("success", "true");
        System.out.println(salesDAO.getSalesList(userId));
        return ResponseEntity.ok().headers(responseHeaders).body(salesDAO.getSalesList(userId));
    }
}
