package com.javainuse.controller;

import com.javainuse.models.Sales;
import com.javainuse.service.PurchasesDAO;
import com.javainuse.service.SalesDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping( path="/transactions" )
public class TransactionsController {

    @Autowired
    PurchasesDAO purchasesDAO;

    @Autowired
    SalesDAO salesDAO;

    @GetMapping("/fetchpurchaseslist/{id}")
    public List<Sales> getPurchasesList(@PathVariable(value = "id") String id) {
        return purchasesDAO.getPurchasesList(id);
    }

    @GetMapping("/fetchsaleslist/{id}")
    public List<Sales> getSalesList(@PathVariable(value = "id") String id) {
        return salesDAO.getSalesList(id);
    }
}
