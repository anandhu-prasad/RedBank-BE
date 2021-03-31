package com.javainuse.controller;

import com.javainuse.analyticsModels.charts.*;
import com.javainuse.analyticsModels.objects.NewBarChart;
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
    //This GET mapping is for getting the stats for the required type
    @GetMapping("/fetchnow")
    public  ResponseEntity<TodaysSale_RespBody> getcurrentStatus (@RequestHeader("Authorization") String userToken){
        Claims claims = jwtTokenUtil.getAllClaimsFromToken(userToken.substring(7));
        String userId = claims.get("userId").toString();
        int userType = Integer.parseInt(claims.get("userType").toString());

        return salesAnalyticsDAO.getToday(userId, userType);
    }
    //This GET mapping is to get the Analytics of the required type YEAR wise
    @GetMapping("/yearly/{year}/{type}/{reason}")
    public ResponseEntity<NewBarChart> getCurrentYearStats  (@PathVariable(value = "year") String year, @PathVariable(value = "type") int type, @PathVariable(value = "reason") String reason, @RequestHeader("Authorization") String userToken){
        Claims claims = jwtTokenUtil.getAllClaimsFromToken(userToken.substring(7));
        String userId = claims.get("userId").toString();
        int userType = Integer.parseInt(claims.get("userType").toString());

        return salesAnalyticsDAO.getCurrentYearStats(userId,year,type, userType,reason);
    }
    //This GET mapping is to get the Analytics of the required type MONTH wise
    @GetMapping("/monthly/{year}/{month}/{type}/{reason}")
    public ResponseEntity<NewBarChart> getCurrentYearStats  (@PathVariable(value = "year") String year,@PathVariable(value = "month") String month ,@PathVariable(value = "type") int type,@PathVariable(value = "reason") String reason, @RequestHeader("Authorization") String userToken){
        Claims claims = jwtTokenUtil.getAllClaimsFromToken(userToken.substring(7));
        String userId = claims.get("userId").toString();
        int userType = Integer.parseInt(claims.get("userType").toString());

        return salesAnalyticsDAO.getSelectedMonthStats(userId,year,month,type, userType,reason);
    }


}