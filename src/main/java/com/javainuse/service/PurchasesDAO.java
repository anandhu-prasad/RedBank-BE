package com.javainuse.service;



import com.javainuse.models.Sales;

import com.javainuse.repositories.SalesRepo;
import com.javainuse.responses.Purchases_RespBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PurchasesDAO {

    @Autowired
    SalesRepo salesRepo;

    @Autowired
    ProfileBbRepo profileBbRepo;

    @Autowired
    ProfileHosRepo profileHosRepo;

    @Autowired
    ProfileIndRepo profileIndRepo;

    public List<Purchases_RespBody> getPurchasesList (String id) {

        List<Sales> salesList = salesRepo.findBySellerId(id);
        List<Purchases_RespBody> results = new ArrayList<>();

        for(Sales item : salesList){
            // accessing only BB repo because only BB can be a seller
            // doubt -> what would be the purchases id ?
            // fix date bug -> convert localdate to timestamp
            results.add(new Purchases_RespBody( item.getSales_id(), item.getDate(),
                    profileBbRepo.findByUserId(item.getSeller_id()).getName(), profileBbRepo.findByUserId(item.getSeller_id()).getEmail(),
                    profileBbRepo.findByUserId(item.getSeller_id()).getPhone1(), item.getComponent(),
                    item.getBlood_group(), item.getUnits(), item.getPrice()));
        }
        return results;
    }
}
