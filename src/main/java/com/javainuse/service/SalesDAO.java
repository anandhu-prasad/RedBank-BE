package com.javainuse.service;



import com.javainuse.analyticsModels.objects.NewBarChart;
import com.javainuse.models.Sales;
import com.javainuse.repositories.*;



import com.javainuse.repositories.ProfileBbRepo;
import com.javainuse.repositories.ProfileHosRepo;
import com.javainuse.repositories.ProfileIndRepo;
import com.javainuse.repositories.SalesRepo;
import com.javainuse.responses.Sales_RespBody;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class SalesDAO {

    @Autowired
    SalesRepo salesRepo;

    @Autowired
    InventoryBbRepo inventoryBbRepo;

    @Autowired
    ProfileBbRepo profileBbRepo;

    @Autowired
    ProfileHosRepo profileHosRepo;

    @Autowired
    ProfileIndRepo profileIndRepo;


    @Autowired
    NotificationRepo notificationRepo;


    public ResponseEntity<List<Sales_RespBody>> getSalesList (String id, int userType) {

        HttpHeaders responseHeaders = new HttpHeaders();

        if( userType == 3){
            // id -> current user id
            // list of transactions which contains user id
            List<Sales> salesList = salesRepo.findBySellerId(id);

            // list to store results
            List<Sales_RespBody> results = new ArrayList<>();

            for( Sales item : salesList){
                // if-else logic -> because buyer can be either a individual, blood bank, hospital
                if(item.getBuyer().substring(0,3).equals("BOB")){
                    results.add(new Sales_RespBody(item.getSales_id(), item.getDate(),
                            profileBbRepo.findByUserId(item.getBuyer()).getName(),  profileBbRepo.findByUserId(item.getBuyer()).getEmail(),
                            profileBbRepo.findByUserId(item.getBuyer()).getPhone1(), item.getComponent(), item.getBlood_group(),
                            item.getUnits(), item.getPrice(), item.getReason(), item.getLocation() ));
                }
                else if(item.getBuyer().substring(0,3).equals("HOS")) {
                    results.add(new Sales_RespBody(item.getSales_id(), item.getDate(),
                            profileHosRepo.findByUserId(item.getBuyer()).getName(), profileHosRepo.findByUserId(item.getBuyer()).getEmail(),
                            profileHosRepo.findByUserId(item.getBuyer()).getPhone1(), item.getComponent(), item.getBlood_group(),
                            item.getUnits(), item.getPrice(), item.getReason(), item.getLocation()));
                }
                else{
                    results.add(new Sales_RespBody(item.getSales_id(), item.getDate(),
                            profileIndRepo.findByUserId(item.getBuyer()).getName(), profileIndRepo.findByUserId(item.getBuyer()).getEmail(),
                            profileIndRepo.findByUserId(item.getBuyer()).getPhone(), item.getComponent(), item.getBlood_group(),
                            item.getUnits(), item.getPrice(), item.getReason(), item.getLocation()));
                }
            }

            responseHeaders.set("success", "true");
            return ResponseEntity.ok().headers(responseHeaders).body(results);

        }else{
            responseHeaders.set("error", "unauthorized");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).headers(responseHeaders).build();
        }



    }


}