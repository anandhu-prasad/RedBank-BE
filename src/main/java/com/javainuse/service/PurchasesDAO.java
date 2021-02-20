package com.javainuse.service;



import com.javainuse.models.Sales;

import com.javainuse.repositories.SalesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchasesDAO {

    @Autowired
    SalesRepo salesRepo;

    public List<Sales> getPurchasesList (String id) { return salesRepo.findByBuyerId(id); }
}
