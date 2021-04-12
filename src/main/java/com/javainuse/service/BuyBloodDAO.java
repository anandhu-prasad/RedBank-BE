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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    InventoryHosRepo inventoryHosRepo;

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

    private Boolean updateinventory(InventoryBb inventoryBb, String bloodGroup, int units, int userType) {

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

//        if(userType == 2){
//            inventoryBb.getComponent();
//            inventoryHosRepo.fi
//        }
//        else if( userType == 3){
//
//        }
//        else{
//            System.out.println("Individual");
//        }

        return status;
    }
    private Boolean updatecustbbinventory(InventoryBb inventoryBb, String bloodGroup, int units, int userType) {

        boolean status = false;

        if(bloodGroup.equals("A+") ){
            inventoryBb.setaPosUnits(inventoryBb.getaPosUnits() + units);
            status= true;
        } else if(bloodGroup.equals("A-")){

            inventoryBb.setaNegUnits(inventoryBb.getaNegUnits() + units);
            status= true;

        } else if(bloodGroup.equals("B+")){

            inventoryBb.setbPosUnits(inventoryBb.getbPosUnits() + units);
            status= true;



        } else if(bloodGroup.equals("B-")){
            if(inventoryBb.getbNegUnits()>=units){
                inventoryBb.setbNegUnits(inventoryBb.getbNegUnits() + units);
                status= true;
            } else {
                status = false;
            }

        } else if(bloodGroup.equals("AB+")){
            inventoryBb.setAbPosUnits(inventoryBb.getAbPosUnits() + units);
            status= true;

        } else if(bloodGroup.equals("AB-")){
            inventoryBb.setAbNegUnits(inventoryBb.getAbNegUnits() + units);
            status= true;

        } else if(bloodGroup.equals("O+")){
            inventoryBb.setoPosUnits(inventoryBb.getoPosUnits() + units);
            status= true;

        } else if(bloodGroup.equals("O-")){
            inventoryBb.setoNegUnits(inventoryBb.getoNegUnits() + units);
            status= true;

        }
        inventoryBbRepo.save(inventoryBb);

        return status;
    }
    private Boolean updatecusthosinventory(InventoryHos inventoryBb, String bloodGroup, int units, int userType) {

        boolean status = false;

        if(bloodGroup.equals("A+") ){
            inventoryBb.setaPosUnits(inventoryBb.getaPosUnits() + units);
            status= true;

        } else if(bloodGroup.equals("A-")){
            inventoryBb.setaNegUnits(inventoryBb.getaNegUnits() + units);
            status= true;

        } else if(bloodGroup.equals("B+")){
            inventoryBb.setbPosUnits(inventoryBb.getbPosUnits() + units);
            status= true;
        } else if(bloodGroup.equals("B-")){
            inventoryBb.setbNegUnits(inventoryBb.getbNegUnits() + units);
            status= true;
        } else if(bloodGroup.equals("AB+")){
            inventoryBb.setAbPosUnits(inventoryBb.getAbPosUnits() + units);
            status= true;

        } else if(bloodGroup.equals("AB-")){
            inventoryBb.setAbNegUnits(inventoryBb.getAbNegUnits() + units);
            status= true;

        } else if(bloodGroup.equals("O+")){
            inventoryBb.setoPosUnits(inventoryBb.getoPosUnits() + units);
            status= true;

        } else if(bloodGroup.equals("O-")){
            inventoryBb.setoNegUnits(inventoryBb.getoNegUnits() +units);
            status= true;

        }
        inventoryHosRepo.save(inventoryBb);

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

    public ResponseEntity<?> submitSale(String userId, ConfirmBuy_ReqBody data, Integer userType) {
        HttpHeaders responseHeaders = new HttpHeaders();

        if(!(userType == 1 ) && data.getLocation()  != null && !data.getLocation().equals("")  && !data.getLocation().equals("N/A")){
            responseHeaders.set("error", "Invalid Request Body");
            return ResponseEntity.badRequest().headers(responseHeaders).build();

        }

        // getting the current timestamp
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        Boolean updatecustomer = false;

        InventoryBb inventoryBb = inventoryBbRepo.findByUserIdAndComponent(data.getSellerId(),data.getComponent());
        Double price = getprice(inventoryBb, data.getBloodGroup()); // getting price
        Boolean status = updateinventory(inventoryBb,data.getBloodGroup(),data.getUnits(), userType);

        if (status == true){

            if(userType == 3){
                InventoryBb inventoryBbCustomer = inventoryBbRepo.findByUserIdAndComponent(userId,data.getComponent());
                updatecustomer = updatecustbbinventory(inventoryBbCustomer,data.getBloodGroup(),data.getUnits(), 0);

                System.out.println("updating bloodbank inv");
            } else
            if(userType == 2){
                InventoryHos inventoryHosCustomer = inventoryHosRepo.findByUserIdAndComponent(userId,data.getComponent());
                updatecustomer = updatecusthosinventory(inventoryHosCustomer,data.getBloodGroup(),data.getUnits(), 2);

            }
            Sales neworder = new Sales(data.getSellerId(), userId, data.getComponent(), data.getBloodGroup(), data.getUnits(), price , timestamp, data.getReason(), data.getLocation());
            salesRepo.save(neworder);
        } else {
            responseHeaders.set("error", "Requested item is sold out! Please try again later.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).headers(responseHeaders).body(new SuccessResponseBody(false));
        }


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

            responseHeaders.set("success", "true");
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

            return ResponseEntity.ok().headers(responseHeaders).body(new SuccessResponseBody(true));
        } else {
            responseHeaders.set("error", "Requested item is sold out! Please try again later.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).headers(responseHeaders).body(new SuccessResponseBody(false));

        }

    }

    private Double getprice(InventoryBb inventoryBb, String bloodGroup) {
        double price = 0.0;
        if(bloodGroup.equals("A+") ){
            price = inventoryBb.getaPosPrice();
        } else if(bloodGroup.equals("A-")){
            price = inventoryBb.getaNegPrice();
        } else if(bloodGroup.equals("B+")){
            price = inventoryBb.getbPosPrice();
        } else if(bloodGroup.equals("B-")){
            price = inventoryBb.getbNegPrice();
        } else if(bloodGroup.equals("AB+")){
            price = inventoryBb.getAbPosPrice();
        } else if(bloodGroup.equals("AB-")){
            price = inventoryBb.getAbNegPrice();
        } else if(bloodGroup.equals("O+")){
            price = inventoryBb.getoPosPrice();
        } else if(bloodGroup.equals("O-")){
            price = inventoryBb.getoNegPrice();
        }
        return  price;
    }
}
