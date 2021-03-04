package com.javainuse.controller;

import com.javainuse.analyticsModels.charts.BarChartMonth;
//import com.javainuse.analyticsModels.charts.InventoryPieChart;
import com.javainuse.config.JwtTokenUtil;
import com.javainuse.models.InventoryBb;
import com.javainuse.models.InventoryHos;
import com.javainuse.repositories.InventoryBbRepo;
import com.javainuse.repositories.InventoryHosRepo;
import com.javainuse.service.InventoryDAO;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    InventoryDAO inventoryDAO;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    //? FETCH CURRENT USER'S INVENTORY
    //! TESTED
    @GetMapping("/receieveinventory")
    public ResponseEntity<List<?>> extractInventory(@RequestHeader ("Authorization") String userToken){

        Claims claims = jwtTokenUtil.getAllClaimsFromToken(userToken.substring(7));
        String userId = claims.get("userId").toString();
        int userType = Integer.parseInt(claims.get("userType").toString());

        return inventoryDAO.extractInventory(userId, userType);
           }

    //? TO UPDATE THE INVENTORY OF CURRENT USER / HOSPITAL.
    //! TESTED
    @PutMapping("/updatehosinventory")
    public ResponseEntity<List<InventoryHos>> updateHosInventory(@RequestBody List<InventoryHos> inventoryHosList, @RequestHeader ("Authorization") String userToken){
        Claims claims = jwtTokenUtil.getAllClaimsFromToken(userToken.substring(7));

        String userId = claims.get("userId").toString();
        return inventoryDAO.updateHosInventory(userId, inventoryHosList);
    }


    //? TO UPDATE THE INVENTORY OF CURRENT USER / BLOOD BANK.
    //? TESTED
    @PutMapping("/updatebbinventory")
    public ResponseEntity<List<InventoryBb>> updateBbInventory(@RequestBody List<InventoryBb> inventoryBbList, @RequestHeader ("Authorization") String userToken){
        Claims claims = jwtTokenUtil.getAllClaimsFromToken(userToken.substring(7));
        String userId = claims.get("userId").toString();
        int userType = Integer.parseInt(claims.get("userType").toString());

        return inventoryDAO.updateBbInventory(userId, inventoryBbList);

    }
//    @GetMapping("/piechart/{type}")
//    public ResponseEntity<List<InventoryPieChart>> getCurrentYearStats  (@PathVariable(value = "type") int type, @RequestHeader("Authorization") String userToken){
//        Claims claims = jwtTokenUtil.getAllClaimsFromToken(userToken.substring(7));
//        String userId = claims.get("userId").toString();
//
//        HttpHeaders responseHeaders = new HttpHeaders();
//        responseHeaders.set("success", "true");
//
//        return ResponseEntity.ok().headers(responseHeaders).body(InventoryDAO.getInventoryStats(userId,type));
//    }

}
