package com.javainuse.service;


import com.javainuse.models.Sales;
import com.javainuse.repositories.SalesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesDAO {

    @Autowired
    SalesRepo salesRepo;

    public List<Sales> getSalesList (String id) {
        return salesRepo.findBySellerId(id); }
}