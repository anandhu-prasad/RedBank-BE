package com.javainuse.controller;

import com.javainuse.config.JwtTokenUtil;
import com.javainuse.models.InventoryBb;
import com.javainuse.models.ProfileBb;
import com.javainuse.models.Sales;
import com.javainuse.repositories.InventoryBbRepo;
import com.javainuse.repositories.ProfileBbRepo;
import com.javainuse.requests.BuyBlood_ReqBody;
import com.javainuse.requests.ConfirmBuy_ReqBody;
import com.javainuse.responses.BuyBlood_RespBody;
import com.javainuse.responses.SuccessResponseBody;
import com.javainuse.service.SalesDAO;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/buyblood")
public class BuyBloodController {
    @Autowired
    InventoryBbRepo inventoryBbRepo;
    @Autowired
    public SalesDAO salesDAO;
    @Autowired
    ProfileBbRepo profileBbRepo;
    @Autowired
    JwtTokenUtil jwtTokenUtil;

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


    @PostMapping("/findbb")
    public List<BuyBlood_RespBody> getBloodBanksList(@RequestBody BuyBlood_ReqBody data , @RequestHeader("Authorization") String userToken) {
        Claims claims = jwtTokenUtil.getAllClaimsFromToken(userToken.substring(7));
        String userId = claims.get("userId").toString();
        Integer userType = Integer.parseInt(claims.get("userType").toString());

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("success", "true");

        List<BuyBlood_RespBody> responseList = new ArrayList<>();

        if(data.getState().equals("All")){
            if(data.getDistrict().equals("All")){
                if(data.getPincode().equals("")) {
                   List<ProfileBb> profileBbList = profileBbRepo.findAll();
                    for (ProfileBb profileBb : profileBbList) {
                        InventoryBb inventoryBb = inventoryBbRepo.findByUserIdAndComponent(profileBb.getUserId(), data.getComponent());
                        double price = compareUnits(inventoryBb, data.getBloodGroup(), data.getReqUnits());
                        // TODO add to a new Array list after comparison
                        if (price != -1) {
                            responseList.add(new BuyBlood_RespBody(profileBb.getUserId(), profileBb.getName(), profileBb.getEmail(), price, profileBb.getPhone1()));
                        }
                    }
                } else {
                    List<ProfileBb> profileBbList = profileBbRepo.findByPincode(data.getPincode());
                    for (ProfileBb profileBb : profileBbList) {
                        InventoryBb inventoryBb = inventoryBbRepo.findByUserIdAndComponent(profileBb.getUserId(), data.getComponent());
                        double price = compareUnits(inventoryBb, data.getBloodGroup(), data.getReqUnits());
                        if (price != -1) {
                            responseList.add(new BuyBlood_RespBody(profileBb.getUserId(), profileBb.getName(), profileBb.getEmail(), price, profileBb.getPhone1()));
                        }
                    }
                }
            } else {
                List<ProfileBb> profileBbList = profileBbRepo.findByDistrictAndPincode(data.getDistrict(),data.getPincode());
                    for (ProfileBb profileBb : profileBbList) {
                        InventoryBb inventoryBb = inventoryBbRepo.findByUserIdAndComponent(profileBb.getUserId(), data.getComponent());
                        double price = compareUnits(inventoryBb, data.getBloodGroup(), data.getReqUnits());
                        if (price != -1) {
                            responseList.add(new BuyBlood_RespBody(profileBb.getUserId(), profileBb.getName(), profileBb.getEmail(), price, profileBb.getPhone1()));
                        }
                    }
                }
            } else {
            List<ProfileBb> profileBbList = profileBbRepo.findByStateAndDistrictAndPincode(data.getState(),data.getDistrict(), data.getPincode());
            for (ProfileBb profileBb : profileBbList) {
                InventoryBb inventoryBb = inventoryBbRepo.findByUserIdAndComponent(profileBb.getUserId(), data.getComponent());
                double price = compareUnits(inventoryBb, data.getBloodGroup(), data.getReqUnits());
                if (price != -1) {
                    responseList.add(new BuyBlood_RespBody(profileBb.getUserId(), profileBb.getName(), profileBb.getEmail(), price, profileBb.getPhone1()));
                }
            }
        }

        return responseList;
    }

   @PostMapping("/confirmbuy")
    public ResponseEntity<SuccessResponseBody> confirmbuy(@RequestBody ConfirmBuy_ReqBody data, @RequestHeader("Authorization") String userToken){

       Claims claims = jwtTokenUtil.getAllClaimsFromToken(userToken.substring(7));
       String userId = claims.get("userId").toString();
       Integer userType = Integer.parseInt(claims.get("userType").toString());




        return salesDAO.submitSale(userId,data,userType);
   }
}
