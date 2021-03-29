package com.javainuse.service;

import com.javainuse.analyticsModels.charts.*;
import com.javainuse.analyticsModels.objects.BarChartMonthObject;
import com.javainuse.analyticsModels.objects.BarChartObject;
import com.javainuse.analyticsModels.objects.DatasetObject;
import com.javainuse.analyticsModels.objects.NewBarChart;
import com.javainuse.models.Sales;
import com.javainuse.repositories.SalesRepo;
import com.javainuse.responses.TodaysSale_RespBody;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

@Service
public class SalesAnalyticsDAO {
    @Autowired
    SalesRepo salesRepo;

    public ResponseEntity<TodaysSale_RespBody> getToday(String userId, int userType) {

        HttpHeaders responseHeaders = new HttpHeaders();
        List<Sales> sellerSalesList = salesRepo.findBySellerId(userId);
        List<Sales> buyerSalesList = salesRepo.findByBuyerId(userId);

        Calendar calendar = new GregorianCalendar();
        Date todaysDate = new Date();
       // todaysDate.setTime(calendar.getTimeInMillis());
//        calendar.setTime(todaysDate);
        System.out.println(todaysDate);
        Calendar today = Calendar.getInstance();
        
        Integer unitsSold = 0;
        Double amountCollected = 00.00;
        Integer unitsBought = 0;
        Double amountSpent = 00.00;

        //List<TodaysSale_RespBody todaysSale_respBodies = new ArrayList<>(Arrays.asList(0,0,0,0));
        //Date ts = sales.getDate() ;
       // Timestamp ts = new Timestamp(System.currentTimeMillis());


        if( userType != 1) {
            for (Sales sales : sellerSalesList) {

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

                String saleDate = simpleDateFormat.format(sales.getDate());

                if (saleDate.compareTo(String.valueOf(LocalDate.now())) == 0) {

                    if (userType == 3) {
                        if (sales.getSeller_id().equals(userId)) {
                            unitsSold = unitsSold + sales.getUnits();
                            amountCollected = amountCollected + (sales.getPrice() * sales.getUnits());

                        }
                    }


                    if (sales.getBuyer().equals(userId)) {
                        unitsBought = unitsBought + sales.getUnits();
                        amountSpent = amountSpent + (sales.getPrice() * sales.getUnits());

                    }
                }

            }


            for (Sales sales : buyerSalesList) {

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String saleDate = simpleDateFormat.format(sales.getDate());

                if (saleDate.compareTo(String.valueOf(LocalDate.now())) == 0) {

                    if (sales.getBuyer().equals(userId)) {
                        unitsBought = unitsBought + sales.getUnits();
                        amountSpent = amountSpent + (sales.getPrice() * sales.getUnits());

                    }

                }

            }

            responseHeaders.set("success", "true");
            return ResponseEntity.ok().headers(responseHeaders).body(new TodaysSale_RespBody(unitsSold, amountCollected, unitsBought, amountSpent));
        }
        else{
            responseHeaders.set("error", "unauthorized");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).headers(responseHeaders).build();
        }


    }




    public ResponseEntity<NewBarChart> getCurrentYearStats(String userId, String year, int type, int userType) {

        // getting all users by sellerid and buyerid
        List<Sales> sellerSalesList = salesRepo.findBySellerId(userId);
        List<Sales> buyerSalesList = salesRepo.findByBuyerId(userId);
        HttpHeaders responseHeaders = new HttpHeaders();

        // date formatter
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
        SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
        Calendar calendar = new GregorianCalendar();


        // Defining Objects of each component
        List<Double> bloodObject = new ArrayList<Double>(Arrays.asList(0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0));
        List<Double> plasmaObject = new ArrayList<Double>(Arrays.asList(0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0));
        List<Double> plateletObject = new ArrayList<Double>(Arrays.asList(0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0));

        //Month Array
        List<String> months = new ArrayList<>(Arrays.asList("01","02","03","04","05","06","07","08","09","10","11","12")) ;

        ///type = 0 => revenue
        ///type = 1 => sold
        ///type = 2 => bought
        ///type = 3 => spent

        if(userType != 1){
            if(type == 0 && userType == 3){
                for(Sales sales : sellerSalesList){
                    String saleDate = yearFormat.format(sales.getDate()); // getting  all sales with year filter

                    if(saleDate.equals(year)) {
                        String saleMonth = monthFormat.format(sales.getDate());  // getting  all sales with month filter

                        for (int i = 0; i<months.size(); i++ ){
                            if(saleMonth.equals(months.get(i))) {

                                String component = sales.getComponent();

//                            TESING PURPOSE
//                            System.out.println("Objects:");
//                            System.out.println(bloodObject);
//                            System.out.println(plasmaObject);
//                            System.out.println(plateletObject);
//                            System.out.println(component);

//                            Filtering by type of component

                                if(component.equals("Blood")){
                                    Double revenue = (sales.getUnits()* sales.getPrice());
                                    bloodObject.set(i,(bloodObject.get(i)+revenue));
                                } else if (component.equals("Plasma")){
                                    Double revenue = (sales.getUnits()* sales.getPrice());
                                    plasmaObject.set(i,(plasmaObject.get(i)+revenue));
                                } else if (component.equals("Platelets")){
                                    Double revenue = (sales.getUnits()* sales.getPrice());
                                    plateletObject.set(i, (plateletObject.get(i)+revenue));
                                }
                            }
                        }
                    }
                }
            }

            if(type == 1 && userType == 3){
                for(Sales sales : sellerSalesList){
                    String saleDate = yearFormat.format(sales.getDate());

                    if(saleDate.equals(year)) {
                        String saleMonth = monthFormat.format(sales.getDate());

                        for (int i = 0; i<months.size(); i++ ){
                            if(saleMonth.equals(months.get(i))) {
                                String component = sales.getComponent();
                                if(component.equals("Blood")){
                                    int sold = (sales.getUnits());
                                    bloodObject.set(i,(bloodObject.get(i)+sold));
                                } else if (component.equals("Plasma")){
                                    int sold = (sales.getUnits());
                                    plasmaObject.set(i,(plasmaObject.get(i)+sold));
                                } else if (component.equals(
                                        "Platelets")){
                                    int sold = (sales.getUnits());
                                    plateletObject.set(i, (plateletObject.get(i)+sold));
                                }
                            }
                        }
                    }
                }
            }

            if(type == 2 ){
                for(Sales sales : buyerSalesList){
                    String saleDate = yearFormat.format(sales.getDate());

                    if(saleDate.equals(year)) {
                        String saleMonth = monthFormat.format(sales.getDate());

                        for (int i = 0; i<months.size(); i++ ){
                            if(saleMonth.equals(months.get(i))) {
                                String component = sales.getComponent();
                                if(component.equals("Blood")){
                                    int bought = (sales.getUnits());
                                    bloodObject.set(i,(bloodObject.get(i)+bought));
                                } else if (component.equals("Plasma")){
                                    int bought = (sales.getUnits());
                                    plasmaObject.set(i,(plasmaObject.get(i)+bought));
                                } else if (component.equals("Platelets")){
                                    int bought = (sales.getUnits());
                                    plateletObject.set(i,(plateletObject.get(i)+bought));
                                }
                            }
                        }
                    }
                }
            }
            if(type == 3){
                for(Sales sales : buyerSalesList){
                    String saleDate = yearFormat.format(sales.getDate());

                    if(saleDate.equals(year)) {
                        String saleMonth = monthFormat.format(sales.getDate());

                        for (int i = 0; i<months.size(); i++ ){
                            if(saleMonth.equals(months.get(i))) {
                                String component = sales.getComponent();
                                if(component.equals("Blood")){
                                    Double spent = (sales.getUnits()* sales.getPrice());
                                    bloodObject.set(i,(bloodObject.get(i)+spent));
                                } else if (component.equals("Plasma")){
                                    Double spent = (sales.getUnits()* sales.getPrice());
                                    plasmaObject.set(i,(plasmaObject.get(i)+spent));
                                } else if (component.equals("Platelets")){
                                    Double spent = (sales.getUnits()* sales.getPrice());
                                    plateletObject.set(i, (plateletObject.get(i)+spent));
                                }
                            }
                        }
                    }
                }
            }

            responseHeaders.set("success", "true");
            return ResponseEntity.ok().headers(responseHeaders).body(new NewBarChart(bloodObject,plasmaObject,plateletObject));

        }else{
            responseHeaders.set("error", "unauthorized");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).headers(responseHeaders).build();
        }




    }

    public ResponseEntity<NewBarChart> getSelectedMonthStats(String userId, String year, String month, int type, int userType) {

        ///type = 0 => revenue
        ///type = 1 => sold
        ///type = 2 => bought
        ///type = 3 => spent

        /// month in format Eg "01" for January
        /// year in format Eg "1998"

        //Month Array

        HttpHeaders responseHeaders = new HttpHeaders();
        List<Sales> sellerSalesList = salesRepo.findBySellerId(userId);
        List<Sales> buyerSalesList = salesRepo.findByBuyerId(userId);

        List<Double> bloodObject = new ArrayList<Double>(Arrays.asList(0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0));
        List<Double> plasmaObject = new ArrayList<Double>(Arrays.asList(0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0));
        List<Double> plateletObject = new ArrayList<Double>(Arrays.asList(0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0));

        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
        SimpleDateFormat monthFormat = new SimpleDateFormat("MM");

        final List<String> bloodGroups = new ArrayList<>(Arrays.asList("A+","A-","B+","B-","AB+", "AB-", "O+","O-"));

        if( userType !=1 ){
            if(type == 0 && userType == 3){
                for(Sales sales : sellerSalesList) {
                    String saleDate = yearFormat.format(sales.getDate()); // getting  all sales with year filter
                    if (saleDate.equals(year)) {
                        String saleMonth = monthFormat.format(sales.getDate());  // getting  all sales with month filter
                        if (saleMonth.equals(month)) {
                            String component = sales.getComponent();
//                            TESING PURPOSE
//                            System.out.println("Objects:");
//                            System.out.println(bloodObject);
//                            System.out.println(plasmaObject);
//                            System.out.println(plateletObject);
//                            System.out.println(component);

//                            Filtering by type of component
                            int index = bloodGroups.indexOf(sales.getBlood_group());
                            if (component.equals("Blood")) {
                                Double revenue = (sales.getUnits() * sales.getPrice());
                                bloodObject.set(index, (bloodObject.get(index) + revenue));
                            } else if (component.equals("Plasma")) {
                                Double revenue = (sales.getUnits() * sales.getPrice());
                                plasmaObject.set(index, (plasmaObject.get(index) + revenue));
                            } else if (component.equals("Platelets")) {
                                Double revenue = (sales.getUnits() * sales.getPrice());
                                plateletObject.set(index, (plateletObject.get(index) + revenue));

                            }
                        }
                    }
                }
            }

            if(type == 1 && userType == 3){
                for(Sales sales : sellerSalesList) {
                    String saleDate = yearFormat.format(sales.getDate()); // getting  all sales with year filter
                    if (saleDate.equals(year)) {
                        String saleMonth = monthFormat.format(sales.getDate());  // getting  all sales with month filter
                        if (saleMonth.equals(month)) {
                            String component = sales.getComponent();
                            int index = bloodGroups.indexOf(sales.getBlood_group());
                            if (component.equals("Blood")) {
                                int sold = sales.getUnits();
                                bloodObject.set(index, (bloodObject.get(index) + sold));
                            } else if (component.equals("Plasma")) {
                                int sold = sales.getUnits();
                                plasmaObject.set(index, (plasmaObject.get(index) + sold));
                            } else if (component.equals("Platelets")) {
                                int sold = sales.getUnits();
                                plateletObject.set(index, (plateletObject.get(index) + sold));

                            }
                        }
                    }
                }
            }

            if(type == 2){
                for(Sales sales : buyerSalesList) {
                    String saleDate = yearFormat.format(sales.getDate()); // getting  all sales with year filter
                    if (saleDate.equals(year)) {
                        String saleMonth = monthFormat.format(sales.getDate());  // getting  all sales with month filter
                        if (saleMonth.equals(month)) {
                            String component = sales.getComponent();
                            int index = bloodGroups.indexOf(sales.getBlood_group());
                            if (component.equals("Blood")) {
                                int bought = sales.getUnits();
                                bloodObject.set(index, (bloodObject.get(index) + bought));
                            } else if (component.equals("Plasma")) {
                                int bought = sales.getUnits();
                                plasmaObject.set(index, (plasmaObject.get(index) + bought));
                            } else if (component.equals("Platelets")) {
                                int bought = sales.getUnits();
                                plateletObject.set(index, (plateletObject.get(index) + bought));

                            }
                        }
                    }
                }
            }

            if(type == 3){
                for(Sales sales : buyerSalesList) {
                    String saleDate = yearFormat.format(sales.getDate()); // getting  all sales with year filter
                    if (saleDate.equals(year)) {
                        String saleMonth = monthFormat.format(sales.getDate());  // getting  all sales with month filter
                        if (saleMonth.equals(month)) {
                            String component = sales.getComponent();
                            int index = bloodGroups.indexOf(sales.getBlood_group());
                            if (component.equals("Blood")) {
                                Double spent = (sales.getUnits() * sales.getPrice());
                                bloodObject.set(index, (bloodObject.get(index) + spent));
                            } else if (component.equals("Plasma")) {
                                Double spent = (sales.getUnits() * sales.getPrice());
                                plasmaObject.set(index, (plasmaObject.get(index) + spent));
                            } else if (component.equals("Platelets")) {
                                Double spent = (sales.getUnits() * sales.getPrice());
                                plateletObject.set(index, (plateletObject.get(index) + spent));

                            }
                        }
                    }
                }
            }

            responseHeaders.set("success", "true");
            return ResponseEntity.ok().headers(responseHeaders).body(new NewBarChart(bloodObject,plasmaObject,plateletObject));

        }else{
            responseHeaders.set("error", "unauthorized");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).headers(responseHeaders).build();
        }

    }
}
