package com.javainuse.controller;

import com.javainuse.analyticsModels.BezierChart;
import com.javainuse.analyticsModels.StackedBarChart;
import com.javainuse.config.JwtTokenUtil;
import com.javainuse.models.Sales;
import com.javainuse.service.SalesDAO;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/salesanalytics")
public class SalesAnalyticsController {

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    SalesDAO salesDAO;


    @GetMapping("/fetchall/{year}")
    public ResponseEntity<BezierChart> getAnalytics(@PathVariable(value = "year") String year, @RequestHeader("Authorization") String userToken){
        Claims claims = jwtTokenUtil.getAllClaimsFromToken(userToken.substring(7));
        String userId = claims.get("userId").toString();

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("success", "true");
        return ResponseEntity.ok().headers(responseHeaders).body(salesDAO.getAnalyticsData(userId,year));
    }

    @GetMapping("/fetchcurrentmonth/{month}")
    public ResponseEntity<StackedBarChart> getCurrentMonth(@PathVariable(value = "month") String month, @RequestHeader("Authorization") String userToken){
        Claims claims = jwtTokenUtil.getAllClaimsFromToken(userToken.substring(7));
        String userId = claims.get("userId").toString();

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("success", "true");
        return ResponseEntity.ok().headers(responseHeaders).body(salesDAO.getCurrentMonthData(userId,month));
    }
}
