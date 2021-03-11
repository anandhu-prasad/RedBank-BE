package com.javainuse.service;

import com.javainuse.models.InventoryBb;
import com.javainuse.models.Notification;
import com.javainuse.models.ProfileBb;
import com.javainuse.models.Sales;
import com.javainuse.repositories.*;
import com.javainuse.requests.BuyBlood_ReqBody;
import com.javainuse.requests.ConfirmBuy_ReqBody;
import com.javainuse.responses.BuyBlood_RespBody;
import com.javainuse.responses.SuccessResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Service
public class BuyBloodDAO {

    @Autowired
    InventoryBbRepo inventoryBbRepo;

    @Autowired
    ProfileBbRepo profileBbRepo;

    @Autowired
    SalesRepo salesRepo;

    @Autowired
    ProfileHosRepo profileHosRepo;

    @Autowired
    ProfileIndRepo profileIndRepo;

    @Autowired
    NotificationRepo notificationRepo;


    private double compareUnits(InventoryBb inventoryBb, String bloodGroup, int reqUnits){
        List<String> bgs = new ArrayList<>(Arrays.asList("A+","A-", "B+","B-","AB+", "AB-", "O+","O-"));
        List<Integer> unitList = new ArrayList<>(Arrays.asList(inventoryBb.getaPosUnits(),inventoryBb.getaNegUnits(),inventoryBb.getbPosUnits(),inventoryBb.getbNegUnits(),
                inventoryBb.getAbPosUnits(),inventoryBb.getAbNegUnits(),inventoryBb.getoPosUnits(),inventoryBb.getoNegUnits()));
        List<Double> priceList = new ArrayList<>(Arrays.asList(inventoryBb.getaPosPrice(),inventoryBb.getaNegPrice(),inventoryBb.getbPosPrice(),
                inventoryBb.getbNegPrice(),inventoryBb.getAbPosPrice(),inventoryBb.getAbNegPrice(),inventoryBb.getoPosPrice(),inventoryBb.getoNegPrice()));
        double price = -1;
        for(int i=0; i<=7; i++){
            if(bloodGroup.equals(bgs.get(i)) &&  unitList.get(i)>=reqUnits) {
                price = priceList.get(i);
            }

        }
        return price;

    }

    private Boolean updateinventory(InventoryBb inventoryBb, String bloodGroup, int units) {

        boolean status = false;

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

    public List<BuyBlood_RespBody> findBloodBanks(BuyBlood_ReqBody data) {

        List<BuyBlood_RespBody> responseList = new ArrayList<>();

        if (data.getState().equals("All") || data.getState().equals("") || data.getState().equals("Select state") || data.getState() == null) {
            if (data.getDistrict().equals("All") || data.getDistrict().equals("") || data.getDistrict().equals("Select district") || data.getDistrict() == null) {
                if (data.getPincode().equals("") || data.getPincode() == null) {
                    List<ProfileBb> profileBbList = profileBbRepo.findAll();
                    for (ProfileBb profileBb : profileBbList) {
                        InventoryBb inventoryBb = inventoryBbRepo.findByUserIdAndComponent(profileBb.getUserId(), data.getComponent());
                        double price = compareUnits(inventoryBb, data.getBloodGroup(), data.getReqUnits());
                        // TODO add to a new Array list after comparison
                        if (price != -1) {
                            responseList.add(new BuyBlood_RespBody(profileBb.getUserId(), profileBb.getName(), profileBb.getEmail(), price, profileBb.getPhone1(), profileBb.getAddress(), profileBb.getDistrict(), profileBb.getState(), profileBb.getPincode()));
                        }
                    }
                } else {
                    List<ProfileBb> profileBbList = profileBbRepo.findByPincode(data.getPincode());
                    for (ProfileBb profileBb : profileBbList) {
                        InventoryBb inventoryBb = inventoryBbRepo.findByUserIdAndComponent(profileBb.getUserId(), data.getComponent());
                        double price = compareUnits(inventoryBb, data.getBloodGroup(), data.getReqUnits());
                        if (price != -1) {
                            responseList.add(new BuyBlood_RespBody(profileBb.getUserId(), profileBb.getName(), profileBb.getEmail(), price, profileBb.getPhone1(), profileBb.getAddress(), profileBb.getDistrict(), profileBb.getState(), profileBb.getPincode()));
                        }
                    }
                }
            } else {
                List<ProfileBb> profileBbList = profileBbRepo.findByDistrictAndPincode(data.getDistrict(), data.getPincode());
                for (ProfileBb profileBb : profileBbList) {
                    InventoryBb inventoryBb = inventoryBbRepo.findByUserIdAndComponent(profileBb.getUserId(), data.getComponent());
                    double price = compareUnits(inventoryBb, data.getBloodGroup(), data.getReqUnits());
                    if (price != -1) {
                        responseList.add(new BuyBlood_RespBody(profileBb.getUserId(), profileBb.getName(), profileBb.getEmail(), price, profileBb.getPhone1(), profileBb.getAddress(), profileBb.getDistrict(), profileBb.getState(), profileBb.getPincode()));
                    }
                }
            }
        } else {
            List<ProfileBb> profileBbList = profileBbRepo.findByStateAndDistrictAndPincode(data.getState(), data.getDistrict(), data.getPincode());
            for (ProfileBb profileBb : profileBbList) {
                InventoryBb inventoryBb = inventoryBbRepo.findByUserIdAndComponent(profileBb.getUserId(), data.getComponent());
                double price = compareUnits(inventoryBb, data.getBloodGroup(), data.getReqUnits());
                if (price != -1) {
                    responseList.add(new BuyBlood_RespBody(profileBb.getUserId(), profileBb.getName(), profileBb.getEmail(), price, profileBb.getPhone1(), profileBb.getAddress(), profileBb.getDistrict(), profileBb.getState(), profileBb.getPincode()));
                }
            }
        }
        return responseList;
    }

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
}
