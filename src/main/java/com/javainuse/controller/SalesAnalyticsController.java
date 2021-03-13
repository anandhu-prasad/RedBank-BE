package com.javainuse.controller;

import com.javainuse.analyticsModels.charts.*;
import com.javainuse.config.JwtTokenUtil;
import com.javainuse.responses.TodaysSale_RespBody;
import com.javainuse.service.SalesAnalyticsDAO;
import com.javainuse.service.SalesDAO;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/salesanalytics")
public class SalesAnalyticsController {

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    SalesAnalyticsDAO salesAnalyticsDAO;

    @GetMapping("/fetchnow")
    public  ResponseEntity<TodaysSale_RespBody> getcurrentStatus (@RequestHeader("Authorization") String userToken){
        Claims claims = jwtTokenUtil.getAllClaimsFromToken(userToken.substring(7));
        String userId = claims.get("userId").toString();

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("success", "true");
        return ResponseEntity.ok().headers(responseHeaders).body(salesAnalyticsDAO.getToday(userId));
    }

    @GetMapping("/yearly/{year}/{type}")
    public ResponseEntity<BarChart> getCurrentYearStats  (@PathVariable(value = "year") String year,@PathVariable(value = "type") int type,@RequestHeader("Authorization") String userToken){
        Claims claims = jwtTokenUtil.getAllClaimsFromToken(userToken.substring(7));
        String userId = claims.get("userId").toString();

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("success", "true");

        return ResponseEntity.ok().headers(responseHeaders).body(salesAnalyticsDAO.getCurrentYearStats(userId,year,type));
    }

    @GetMapping("/monthly/{year}/{month}/{type}")
    public ResponseEntity<BarChartMonth> getCurrentYearStats  (@PathVariable(value = "year") String year,@PathVariable(value = "month") String month ,@PathVariable(value = "type") int type, @RequestHeader("Authorization") String userToken){
        Claims claims = jwtTokenUtil.getAllClaimsFromToken(userToken.substring(7));
        String userId = claims.get("userId").toString();

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("success", "true");

        return ResponseEntity.ok().headers(responseHeaders).body(salesAnalyticsDAO.getSelectedMonthStats(userId,year,month,type));
    }


}
