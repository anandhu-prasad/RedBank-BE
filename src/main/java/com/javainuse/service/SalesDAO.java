package com.javainuse.service;


import com.javainuse.models.InventoryBb;
import com.javainuse.models.Notification;
import com.javainuse.models.Sales;
import com.javainuse.repositories.*;

import com.javainuse.requests.ConfirmBuy_ReqBody;
import com.javainuse.responses.SuccessResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public List<Sales> getSalesList (String id) {
        return salesRepo.findBySellerId(id);
    }

    public ResponseEntity<SuccessResponseBody> submitsale(String userId, ConfirmBuy_ReqBody data, Integer userType) {

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("success", "true");

        Sales neworder = new Sales(data.getSellerId(), userId, data.getComponent(), data.getBloodGroup(), data.getUnits(), data.getPrice(), data.getDate());
        salesRepo.save(neworder);
//        InventoryBb inventoryBb = inventoryBbRepo.findByUserIdAndComponent(data.getSellerId(),data.getComponent());
//        Boolean status = updateinventory(inventoryBb,data.getBloodGroup(),data.getUnits());
        String customer;
        if(userType == 1){
            customer = profileIndRepo.findByUserId(userId).getName();
        } else if (userType == 2){
            customer = profileHosRepo.findByUserId(userId).getName();
        } else {
            customer = profileBbRepo.findByUserId(userId).getName();
        }
        Notification notification = new Notification(data.getSellerId(),"New booking",customer + " has booked "+ data.getUnits() + " units of "+ data.getComponent(),new Timestamp(System.currentTimeMillis()));
        notificationRepo.save(notification);

        return ResponseEntity.ok().headers(responseHeaders).body(new SuccessResponseBody(true));
    }

    private Boolean updateinventory(InventoryBb inventoryBb, String bloodGroup, int units) {
        List<String> bgs = new ArrayList<>(Arrays.asList("A+","A-", "B+","B-","AB+", "AB-", "O+","O-"));
        List<Integer> unitList = new ArrayList<>(Arrays.asList(inventoryBb.getaPosUnits(),inventoryBb.getaNegUnits(),inventoryBb.getbPosUnits(),inventoryBb.getbNegUnits(),
                inventoryBb.getAbPosUnits(),inventoryBb.getAbNegUnits(),inventoryBb.getoPosUnits(),inventoryBb.getoNegUnits()));

        Boolean status = false;
        for(int i=0; i<=7; i++) {
            if (bloodGroup.equals(bgs.get(i))){
                int inventoryunits = unitList.get(i);
                unitList.set(i,inventoryunits-units);
                status= true;
            }
        }

        return status;

    }

}