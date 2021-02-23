package com.javainuse.service;


import com.javainuse.models.Sales;
import com.javainuse.repositories.SalesRepo;

import com.javainuse.requests.ConfirmBuy_ReqBody;
import com.javainuse.responses.SuccessResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesDAO {

    @Autowired
    SalesRepo salesRepo;

    public List<Sales> getSalesList (String id) {
        return salesRepo.findBySellerId(id);
    }

    public List<SuccessResponseBody> submitsale(String userId, ConfirmBuy_ReqBody data) {

        Sales neworder = new Sales(data.getSellerId(), userId, data.getComponent(), data.getBloodGroup(), data.getUnits(), data.getPrice(), data.getDate());
        return (List<SuccessResponseBody>) salesRepo.save(neworder);
    }

}