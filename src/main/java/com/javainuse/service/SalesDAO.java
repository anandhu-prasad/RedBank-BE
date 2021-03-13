package com.javainuse.service;


import com.javainuse.analyticsModels.*;
import com.javainuse.models.InventoryBb;
import com.javainuse.models.Notification;
import com.javainuse.models.Sales;
import com.javainuse.repositories.*;

import com.javainuse.requests.ConfirmBuy_ReqBody;
import com.javainuse.responses.SuccessResponseBody;


import com.javainuse.repositories.ProfileBbRepo;
import com.javainuse.repositories.ProfileHosRepo;
import com.javainuse.repositories.ProfileIndRepo;
import com.javainuse.repositories.SalesRepo;
import com.javainuse.responses.Sales_RespBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.sql.Timestamp;
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

    public SuccessResponseBody submitSale(String userId, ConfirmBuy_ReqBody data, Integer userType) {

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("success", "true");

        Sales neworder = new Sales(data.getSellerId(), userId, data.getComponent(), data.getBloodGroup(), data.getUnits(), data.getPrice(), data.getDate());
        salesRepo.save(neworder);
        InventoryBb inventoryBb = inventoryBbRepo.findByUserIdAndComponent(data.getSellerId(),data.getComponent());
        Boolean status = updateinventory(inventoryBb,data.getBloodGroup(),data.getUnits());
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

        return new SuccessResponseBody(status);
    }

    private Boolean updateinventory(InventoryBb inventoryBb, String bloodGroup, int units) {
//        final List<String> bgs = new ArrayList<>(Arrays.asList("A+","A-", "B+","B-","AB+", "AB-", "O+","O-"));
//        List<Integer> unitList = new ArrayList<>(Arrays.asList(inventoryBb.getaPosUnits(),inventoryBb.getaNegUnits(),inventoryBb.getbPosUnits(),inventoryBb.getbNegUnits(),
//                inventoryBb.getAbPosUnits(),inventoryBb.getAbNegUnits(),inventoryBb.getoPosUnits(),inventoryBb.getoNegUnits()));

        boolean status = false;
//        for(int i=0; i<=7; i++) {
//            if (bloodGroup.equals(bgs.get(i))){
//                //int inventoryunits = unitList.get(i);
//                int funits = unitList.set(i,unitList.get(i)-units);
//
//                //unitList.set(i,inventoryunits-units);
//                status= true;
//            }
 //       }

        if(bloodGroup.equals("A+") ){
            if(inventoryBb.getaPosUnits()>units){
                inventoryBb.setaPosUnits(inventoryBb.getaPosUnits() - units);
                status= true;
            } else {
                status = false;
            }
        } else if(bloodGroup.equals("A-")){
            if(inventoryBb.getaNegUnits()>units){
                inventoryBb.setaNegUnits(inventoryBb.getaNegUnits() - units);
                status= true;
            } else {
                status = false;
            }
        } else if(bloodGroup.equals("B+")){
            if(inventoryBb.getbPosUnits()>units){
                inventoryBb.setbPosUnits(inventoryBb.getbPosUnits() - units);
                status= true;
            } else {
                status = false;
            }


        } else if(bloodGroup.equals("B-")){
            if(inventoryBb.getbNegUnits()>units){
                inventoryBb.setbNegUnits(inventoryBb.getbNegUnits() - units);
                status= true;
            } else {
                status = false;
            }

        } else if(bloodGroup.equals("AB+")){
            if(inventoryBb.getAbPosUnits()>units){
                inventoryBb.setAbPosUnits(inventoryBb.getAbPosUnits() - units);
                status= true;
            } else {
                status = false;
            }
        } else if(bloodGroup.equals("AB-")){
            if(inventoryBb.getAbNegUnits()>units){
                inventoryBb.setAbNegUnits(inventoryBb.getAbNegUnits() - units);
                status= true;
            } else {
                status = false;
            }
        } else if(bloodGroup.equals("O+")){
            if(inventoryBb.getoPosUnits()>units){
                inventoryBb.setoPosUnits(inventoryBb.getoPosUnits() - units);
                status= true;
            } else {
                status = false;
            }
        } else if(bloodGroup.equals("O-")){
            if(inventoryBb.getoNegUnits()>units){
                inventoryBb.setoNegUnits(inventoryBb.getoNegUnits() - units);
                status= true;
            } else {
                status = false;
            }
        }
        inventoryBbRepo.save(inventoryBb);


        return status;

    }

    public List<Sales_RespBody> getSalesList (String id) {

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
                        item.getUnits(), item.getPrice() ));
            }
            else if(item.getBuyer().substring(0,3).equals("HOS")) {
                results.add(new Sales_RespBody(item.getSales_id(), item.getDate(),
                        profileHosRepo.findByUserId(item.getBuyer()).getName(), profileHosRepo.findByUserId(item.getBuyer()).getEmail(),
                        profileHosRepo.findByUserId(item.getBuyer()).getPhone1(), item.getComponent(), item.getBlood_group(),
                        item.getUnits(), item.getPrice()));
            }
            else{
                results.add(new Sales_RespBody(item.getSales_id(), item.getDate(),
                        profileIndRepo.findByUserId(item.getBuyer()).getName(), profileIndRepo.findByUserId(item.getBuyer()).getEmail(),
                        profileIndRepo.findByUserId(item.getBuyer()).getPhone(), item.getComponent(), item.getBlood_group(),
                        item.getUnits(), item.getPrice()));
            }
        }

        return results;
    }


}


//    //       List<PieChart> pieChartList = new ArrayList<>();
//    List<Sales> salesList = salesRepo.findBySellerId("BOB02");
//    //        Date start = new Date();
////        start.setTime(drive.getStartTimestamp().getTime());
//    Integer totalUnits = 0;
//
//        for (int i=0; i<salesList.size(); i++){
//        totalUnits = totalUnits + salesList.g
//        }
//
//        PieChart pieChart= new PieChart("Raju",2345,"rgba(131, 167, 234, 1)","#7F7F7F",15);
//        pieChartList.add(pieChart);


//            for(int j = 0; j<bloodGroups.size(); j++){
//                if(sales.getBlood_group().equals(bloodGroups.get(j))) {
//                    for (int i = 0; i < component.size(); i++) {
//                        if (sales.getComponent().equals(component.get(i))) {
//                            row.set()
//                            aPos.set(i, aPos.get(i) + sales.getUnits());
//                        }
//                    }
//                }
//
//            }
