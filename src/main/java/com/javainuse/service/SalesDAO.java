package com.javainuse.service;



import com.javainuse.models.Sales;
import com.javainuse.repositories.ProfileBbRepo;
import com.javainuse.repositories.ProfileHosRepo;
import com.javainuse.repositories.ProfileIndRepo;
import com.javainuse.repositories.SalesRepo;
import com.javainuse.responses.Sales_RespBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SalesDAO {

    @Autowired
    SalesRepo salesRepo;

    @Autowired
    ProfileBbRepo profileBbRepo;

    @Autowired
    ProfileHosRepo profileHosRepo;

    @Autowired
    ProfileIndRepo profileIndRepo;

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