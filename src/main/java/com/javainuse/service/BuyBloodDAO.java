package com.javainuse.service;

import com.javainuse.models.*;
import com.javainuse.repositories.*;
import com.javainuse.requests.BuyBlood_ReqBody;
import com.javainuse.requests.ConfirmBuy_ReqBody;
import com.javainuse.responses.BuyBlood_RespBody;
import com.javainuse.responses.FindDonors_RespBody;
import com.javainuse.responses.SuccessResponseBody;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

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

    @Autowired
    AndroidPushNotificationsService androidPushNotificationsService;


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
            if(inventoryBb.getaPosUnits()>=units){
                inventoryBb.setaPosUnits(inventoryBb.getaPosUnits() - units);
                status= true;
            } else {
                status = false;
            }
        } else if(bloodGroup.equals("A-")){
            if(inventoryBb.getaNegUnits()>=units){
                inventoryBb.setaNegUnits(inventoryBb.getaNegUnits() - units);
                status= true;
            } else {
                status = false;
            }
        } else if(bloodGroup.equals("B+")){
            if(inventoryBb.getbPosUnits()>=units){
                inventoryBb.setbPosUnits(inventoryBb.getbPosUnits() - units);
                status= true;
            } else {
                status = false;
            }


        } else if(bloodGroup.equals("B-")){
            if(inventoryBb.getbNegUnits()>=units){
                inventoryBb.setbNegUnits(inventoryBb.getbNegUnits() - units);
                status= true;
            } else {
                status = false;
            }

        } else if(bloodGroup.equals("AB+")){
            if(inventoryBb.getAbPosUnits()>=units){
                inventoryBb.setAbPosUnits(inventoryBb.getAbPosUnits() - units);
                status= true;
            } else {
                status = false;
            }
        } else if(bloodGroup.equals("AB-")){
            if(inventoryBb.getAbNegUnits()>=units){
                inventoryBb.setAbNegUnits(inventoryBb.getAbNegUnits() - units);
                status= true;
            } else {
                status = false;
            }
        } else if(bloodGroup.equals("O+")){
            if(inventoryBb.getoPosUnits()>=units){
                inventoryBb.setoPosUnits(inventoryBb.getoPosUnits() - units);
                status= true;
            } else {
                status = false;
            }
        } else if(bloodGroup.equals("O-")){
            if(inventoryBb.getoNegUnits()>=units){
                inventoryBb.setoNegUnits(inventoryBb.getoNegUnits() - units);
                status= true;
            } else {
                status = false;
            }
        }
        inventoryBbRepo.save(inventoryBb);

        return status;
    }

    public List<BuyBlood_RespBody> findBloodBanks(BuyBlood_ReqBody data, String userId) {
        List<BuyBlood_RespBody> responseList = new ArrayList<>();
        List<ProfileBb> list = profileBbRepo.findAll();

        if(!(data.getState().equals("All") || data.getState().equals("") || data.getState().equals("Select state") || data.getState() == null)){
            list = list.stream().filter( item -> item.getState().equals(data.getState())).collect(Collectors.toList());
        }
        if(!(data.getDistrict().equals("All") || data.getDistrict().equals("") || data.getDistrict().equals("Select district") || data.getDistrict() == null)){
            list = list.stream().filter( item -> item.getDistrict().equals(data.getDistrict())).collect(Collectors.toList());
        }
        if(data.getPincode() != "" && data.getPincode() != null){
            list = list.stream().filter( item -> item.getPincode() == data.getPincode()).collect(Collectors.toList());
        }
        System.out.println(list);
        for(ProfileBb profileBb: list){
            if(!profileBb.getUserId().equals(userId)) {
                InventoryBb inventoryBb = inventoryBbRepo.findByUserIdAndComponent(profileBb.getUserId(), data.getComponent());
                double price = compareUnits(inventoryBb, data.getBloodGroup(), data.getReqUnits());
                if (price != -1) {
                    responseList.add(new BuyBlood_RespBody(profileBb.getUserId(), profileBb.getName(), profileBb.getEmail(), price, profileBb.getPhone1(), profileBb.getAddress(), profileBb.getDistrict(), profileBb.getState(), profileBb.getPincode()));
                    //result = profileBbList.stream().map(item -> new BuyBlood_RespBody(profileBb.getUserId(), profileBb.getName(), profileBb.getEmail(), price, profileBb.getPhone1(), profileBb.getAddress(), profileBb.getDistrict(), profileBb.getState(), profileBb.getPincode())).collect(Collectors.toList());

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

        // SENDING push notification
        if ( status == true){
            Notification notification = new Notification(data.getSellerId(),"New booking",customer + " has booked "+ data.getUnits() + " units of "+ data.getComponent(),new Timestamp(System.currentTimeMillis()));
            notificationRepo.save(notification);

            JSONObject body = new JSONObject();
            body.put("to", "/topics/" + data.getSellerId());
            body.put("priority", "high");

            JSONObject fireNotification = new JSONObject();
            fireNotification.put("title", "New Purchase");
            fireNotification.put("body", customer + " has booked "+ data.getUnits() + " units of "+ data.getComponent() );

            JSONObject data2 = new JSONObject();

            body.put("notification", fireNotification);
            body.put("data", data2);
            HttpEntity request = new HttpEntity<>(body.toString());

            CompletableFuture pushNotification = androidPushNotificationsService.send(request);
            CompletableFuture.allOf(pushNotification).join();
        }


        return new SuccessResponseBody(status);
    }
}


//        List<BuyBlood_RespBody> responseList = new ArrayList<>();
//
//        List<ProfileBb> profileBbList = new ArrayList<>();
//        List<InventoryBb> inventoryBbList = new ArrayList<>();
//        List<BuyBlood_RespBody> result = new ArrayList<>();
//
////        inventoryBbList = inventoryBbRepo.findByComponent(data.getComponent());
//
//        for (ProfileBb profileBb : profileBbList){
//            if(!(data.getState().equals("All") || data.getState().equals("") || data.getState().equals("Select state") || data.getState() == null)){
//                profileBbList = profileBbList.stream().filter( item -> item.getState().equals(data.getState())).collect(Collectors.toList());
//            }
//            if(!(data.getDistrict().equals("All") || data.getDistrict().equals("") || data.getDistrict().equals("Select district") || data.getDistrict() == null)){
//                profileBbList= profileBbList.stream().filter( item -> item.getDistrict().equals(data.getDistrict())).collect(Collectors.toList());
//            }
//            if(data.getPincode() != "" && data.getPincode() != null){
//                profileBbList = profileBbList.stream().filter( item -> item.getPincode() == data.getPincode()).collect(Collectors.toList());
//            }
//            InventoryBb inventoryBb = inventoryBbRepo.findByUserIdAndComponent(profileBb.getUserId(), data.getComponent());
//            double price = compareUnits(inventoryBb, data.getBloodGroup(), data.getReqUnits());
//            if (price != -1) {
//                responseList.add(new BuyBlood_RespBody(profileBb.getUserId(), profileBb.getName(), profileBb.getEmail(), price, profileBb.getPhone1(), profileBb.getAddress(), profileBb.getDistrict(), profileBb.getState(), profileBb.getPincode()));
//                //result = profileBbList.stream().map(item -> new BuyBlood_RespBody(profileBb.getUserId(), profileBb.getName(), profileBb.getEmail(), price, profileBb.getPhone1(), profileBb.getAddress(), profileBb.getDistrict(), profileBb.getState(), profileBb.getPincode())).collect(Collectors.toList());
//
//            }
//        }

//inventoryBbList = inventoryBbList.stream().filter( item -> item.getDonorStatus() == 1).collect(Collectors.toList());




//return result;

//        if (data.getState().equals("All") || data.getState().equals("") || data.getState().equals("Select state") || data.getState() == null) {
//            if (data.getDistrict().equals("All") || data.getDistrict().equals("") || data.getDistrict().equals("Select district") || data.getDistrict() == null) {
//                if (data.getPincode().equals("") || data.getPincode() == null) {
//                    List<ProfileBb> profileBbList = profileBbRepo.findAll();
//                    for (ProfileBb profileBb : profileBbList) {
//                        InventoryBb inventoryBb = inventoryBbRepo.findByUserIdAndComponent(profileBb.getUserId(), data.getComponent());
//                        double price = compareUnits(inventoryBb, data.getBloodGroup(), data.getReqUnits());
//                        // TODO add to a new Array list after comparison
//                        if (price != -1) {
//                            responseList.add(new BuyBlood_RespBody(profileBb.getUserId(), profileBb.getName(), profileBb.getEmail(), price, profileBb.getPhone1(), profileBb.getAddress(), profileBb.getDistrict(), profileBb.getState(), profileBb.getPincode()));
//                        }
//                    }
//                } else {
//                    List<ProfileBb> profileBbList = profileBbRepo.findByPincode(data.getPincode());
//                    for (ProfileBb profileBb : profileBbList) {
//                        InventoryBb inventoryBb = inventoryBbRepo.findByUserIdAndComponent(profileBb.getUserId(), data.getComponent());
//                        double price = compareUnits(inventoryBb, data.getBloodGroup(), data.getReqUnits());
//                        if (price != -1) {
//                            responseList.add(new BuyBlood_RespBody(profileBb.getUserId(), profileBb.getName(), profileBb.getEmail(), price, profileBb.getPhone1(), profileBb.getAddress(), profileBb.getDistrict(), profileBb.getState(), profileBb.getPincode()));
//                        }
//                    }
//                }
//            } else {
//                List<ProfileBb> profileBbList = profileBbRepo.findByDistrictAndPincode(data.getDistrict(), data.getPincode());
//                for (ProfileBb profileBb : profileBbList) {
//                    InventoryBb inventoryBb = inventoryBbRepo.findByUserIdAndComponent(profileBb.getUserId(), data.getComponent());
//                    double price = compareUnits(inventoryBb, data.getBloodGroup(), data.getReqUnits());
//                    if (price != -1) {
//                        responseList.add(new BuyBlood_RespBody(profileBb.getUserId(), profileBb.getName(), profileBb.getEmail(), price, profileBb.getPhone1(), profileBb.getAddress(), profileBb.getDistrict(), profileBb.getState(), profileBb.getPincode()));
//                    }
//                }
//            }
//        } else {
//            List<ProfileBb> profileBbList = profileBbRepo.findByStateAndDistrictAndPincode(data.getState(), data.getDistrict(), data.getPincode());
//            for (ProfileBb profileBb : profileBbList) {
//                InventoryBb inventoryBb = inventoryBbRepo.findByUserIdAndComponent(profileBb.getUserId(), data.getComponent());
//                double price = compareUnits(inventoryBb, data.getBloodGroup(), data.getReqUnits());
//                if (price != -1) {
//                    responseList.add(new BuyBlood_RespBody(profileBb.getUserId(), profileBb.getName(), profileBb.getEmail(), price, profileBb.getPhone1(), profileBb.getAddress(), profileBb.getDistrict(), profileBb.getState(), profileBb.getPincode()));
//                }
//            }
//        }
//       return responseList;