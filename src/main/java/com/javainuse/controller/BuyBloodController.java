package com.javainuse.controller;

import com.javainuse.config.JwtTokenUtil;
import com.javainuse.models.InventoryBb;
import com.javainuse.models.ProfileBb;
import com.javainuse.models.Sales;
import com.javainuse.repositories.InventoryBbRepo;
import com.javainuse.repositories.ProfileBbRepo;
import com.javainuse.repositories.SalesRepo;
import com.javainuse.requests.BuyBlood_ReqBody;
import com.javainuse.requests.ConfirmBuy_ReqBody;
import com.javainuse.responses.BuyBlood_RespBody;
import com.javainuse.responses.SuccessResponseBody;
import com.javainuse.service.SalesDAO;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
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
        List<String> bgs = new ArrayList<>(Arrays.asList("A+", "A-", "B+","B-","AB+", "AB-", "O+","O-"));
        List<Integer> unitList = new ArrayList<>(Arrays.asList(inventoryBb.getaPosUnits(),inventoryBb.getaNegUnits(),inventoryBb.getbPosUnits(),inventoryBb.getbNegUnits(),
                inventoryBb.getAbPosUnits(),inventoryBb.getAbNegUnits(),inventoryBb.getoPosUnits(),inventoryBb.getoNegUnits()));
        List<Double> priceList = new ArrayList<>(Arrays.asList(inventoryBb.getaPosPrice(),inventoryBb.getaNegPrice(),inventoryBb.getbPosPrice(),
                inventoryBb.getbNegPrice(),inventoryBb.getAbPosPrice(),inventoryBb.getAbNegPrice(),inventoryBb.getoPosPrice(),inventoryBb.getoNegPrice()));
        double price = -1;
        for(int i=0; i<=bgs.size(); i++){
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

        List<BuyBlood_RespBody> responseList = new ArrayList<>();

        if( data.getState() == "All"  ){
            if( data.getPincode() == ""){
                List<InventoryBb> inventoryBbList = inventoryBbRepo.findByComponent(data.getComponent());
            } else {
                List<ProfileBb> profileBbList = profileBbRepo.findByPincode(data.getPincode());
                for (ProfileBb profileBb : profileBbList){
                    InventoryBb inventoryBb = inventoryBbRepo.findByUserIdAndComponent(profileBb.getUserId(),data.getComponent());
                    // TODO add to a new Array list after comparison
                    double price = compareUnits(inventoryBb,data.getBloodGroup(), data.getReqUnits());
                    if(price != -1){
                        responseList.add(new BuyBlood_RespBody(profileBb.getUserId(),profileBb.getName(),profileBb.getEmail(),price,profileBb.getPhone1()));
                    }
                }
            }
        } else {
            if( data.getDistrict() == "All" ){
                if (data.getPincode() == ""){
                    List<ProfileBb> profileBbList = profileBbRepo.findByState(data.getState());
                    for (ProfileBb profileBb : profileBbList){
                        InventoryBb inventoryBb = inventoryBbRepo.findByUserIdAndComponent(profileBb.getUserId(),data.getComponent());
                        // TODO add to a new Array list after comparison
                        double price = compareUnits(inventoryBb,data.getBloodGroup(), data.getReqUnits());
                        if(price != -1){
                            responseList.add(new BuyBlood_RespBody(profileBb.getUserId(),profileBb.getName(),profileBb.getEmail(),price,profileBb.getPhone1()));
                        }
                    }
                } else {
                    List<ProfileBb> profileBbList = profileBbRepo.findByStateAndPincode(data.getState(),data.getPincode());
                    for (ProfileBb profileBb : profileBbList){
                        InventoryBb inventoryBb = inventoryBbRepo.findByUserIdAndComponent(profileBb.getUserId(),data.getComponent());
                        // TODO add to a new Array list after comparison
                        double price = compareUnits(inventoryBb,data.getBloodGroup(), data.getReqUnits());
                        if(price != -1){
                            responseList.add(new BuyBlood_RespBody(profileBb.getUserId(),profileBb.getName(),profileBb.getEmail(),price,profileBb.getPhone1()));
                        }
                    }
                }
            } else {
                if( data.getPincode() == ""){
                    List<ProfileBb> profileBbList = profileBbRepo.findByStateAndDistrict(data.getPincode());
                    for (ProfileBb profileBb : profileBbList){
                        InventoryBb inventoryBb = inventoryBbRepo.findByUserIdAndComponent(profileBb.getUserId(),data.getComponent());
                        // TODO add to a new Array list after comparison
                        double price = compareUnits(inventoryBb,data.getBloodGroup(), data.getReqUnits());
                        if(price != -1){
                            responseList.add(new BuyBlood_RespBody(profileBb.getUserId(),profileBb.getName(),profileBb.getEmail(),price,profileBb.getPhone1()));
                        }
                    }
                } else {
                    List<ProfileBb> profileBbList = profileBbRepo.findByStateAndDistrictAndPincode(data.getPincode());
                    for (ProfileBb profileBb : profileBbList){
                        InventoryBb inventoryBb = inventoryBbRepo.findByUserIdAndComponent(profileBb.getUserId(),data.getComponent());
                        // TODO add to a new Array list after comparison
                        double price = compareUnits(inventoryBb,data.getBloodGroup(), data.getReqUnits());
                        if(price != -1){
                            responseList.add(new BuyBlood_RespBody(profileBb.getUserId(),profileBb.getName(),profileBb.getEmail(),price,profileBb.getPhone1()));
                        }
                    }
                }

            }
        }
        return responseList;
    }

   @PostMapping("/comfirmbuy")
    public List<SuccessResponseBody> confirmbuy(@RequestBody ConfirmBuy_ReqBody data, @RequestHeader("Authorization") String userToken){

       Claims claims = jwtTokenUtil.getAllClaimsFromToken(userToken.substring(7));
       String userId = claims.get("userId").toString();
       Integer userType = Integer.parseInt(claims.get("userType").toString());

        return salesDAO.submitsale(userId, data);
   }
}
