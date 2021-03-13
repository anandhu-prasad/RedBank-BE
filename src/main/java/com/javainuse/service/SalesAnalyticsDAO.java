package com.javainuse.service;

import com.javainuse.analyticsModels.charts.*;
import com.javainuse.analyticsModels.objects.BarChartMonthObject;
import com.javainuse.analyticsModels.objects.BarChartObject;
import com.javainuse.analyticsModels.objects.DatasetObject;
import com.javainuse.models.Sales;
import com.javainuse.repositories.SalesRepo;
import com.javainuse.responses.TodaysSale_RespBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

@Service
public class SalesAnalyticsDAO {
    @Autowired
    SalesRepo salesRepo;

    public TodaysSale_RespBody getToday(String userId) {

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

        for(Sales sales: sellerSalesList) {
            //System.out.println(sales.getDate());


            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
           // System.out.println(saleDate);
            String saleDate = simpleDateFormat.format(sales.getDate());

            System.out.println(saleDate);
            System.out.println(LocalDate.now());

            if(saleDate.compareTo(String.valueOf(LocalDate.now())) == 0){
                System.out.println("ok");
                System.out.println(saleDate);
                System.out.println(LocalDate.now());
                        if(sales.getSeller_id().equals(userId)){
                            unitsSold= unitsSold + sales.getUnits();
                            amountCollected= amountCollected + (sales.getPrice() * sales.getUnits());
                            System.out.println("Seller");
                        }
                        System.out.println("buyer"+sales.getBuyer());
                System.out.println("seller"+sales.getSeller_id());
                        if(sales.getBuyer().equals(userId)){
                            unitsBought= unitsBought+ sales.getUnits();
                            amountSpent= amountSpent+ (sales.getPrice() * sales.getUnits());
                            System.out.println("Buyer");
                        }

            }

        }
        for(Sales sales: buyerSalesList) {
            //System.out.println(sales.getDate());


            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            // System.out.println(saleDate);
            String saleDate = simpleDateFormat.format(sales.getDate());

            System.out.println(saleDate);
            System.out.println(LocalDate.now());

            if(saleDate.compareTo(String.valueOf(LocalDate.now())) == 0){
                System.out.println("ok");
                System.out.println(saleDate);
                System.out.println(LocalDate.now());
                System.out.println("buyer"+sales.getBuyer());

                if(sales.getBuyer().equals(userId)){
                    unitsBought= unitsBought+ sales.getUnits();
                    amountSpent= amountSpent+ (sales.getPrice() * sales.getUnits());
                    System.out.println("Buyer");
                }

            }

        }



        return new TodaysSale_RespBody(unitsSold,amountCollected,unitsBought,amountSpent);

    }

    public BarChart getCurrentYearStats(String userId, String year, int type) {
        List<Sales> sellerSalesList = salesRepo.findBySellerId(userId);
        List<Sales> buyerSalesList = salesRepo.findByBuyerId(userId);

        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
        SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
        Calendar calendar = new GregorianCalendar();
        BarChartObject barChartObject = new BarChartObject(Arrays.asList(0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0));
        List<Double> yearObject = new ArrayList<Double>(Arrays.asList(0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0));


        ///type = 0 => revenue
        ///type = 1 => sold
        ///type = 2 => bought


        if(type == 2){
            Double totalBought = 0.0;
            for(Sales sales:buyerSalesList){
                String saleDate = yearFormat.format(sales.getDate());
                String saleYear = Integer.toString(calendar.get(Calendar.YEAR));

                System.out.println(saleDate);
                System.out.println(year);
                System.out.println("ok");

                if(saleDate.equals(year)) {
                    String saleMonth = monthFormat.format(sales.getDate());
                    if(saleMonth.equals("01")){
                        totalBought = totalBought + sales.getUnits();
                        yearObject.set(0,totalBought);
                        System.out.println(totalBought);
                    }
                    if(saleMonth.equals("02")){
                        totalBought = totalBought + sales.getUnits();
                        yearObject.set(1,totalBought);
                    }
                    if(saleMonth.equals("03")){
                        totalBought = totalBought + sales.getUnits();
                        yearObject.set(2,totalBought);
                    }
                    if(saleMonth.equals("04")){
                        totalBought = totalBought + sales.getUnits();
                        yearObject.set(3,totalBought);
                    }
                    if(saleMonth.equals("05")){
                        totalBought = totalBought + sales.getUnits();
                        yearObject.set(4,totalBought);
                    }
                    if(saleMonth.equals("06")){
                        totalBought = totalBought + sales.getUnits();
                        yearObject.set(5,totalBought);
                    }
                    if(saleMonth.equals("07")){
                        totalBought = totalBought + sales.getUnits();
                        yearObject.set(6,totalBought);
                    }
                    if(saleMonth.equals("08")){
                        totalBought = totalBought + sales.getUnits();
                        yearObject.set(7,totalBought);
                    }
                    if(saleMonth.equals("09")){
                        totalBought = totalBought + sales.getUnits();
                        yearObject.set(8,totalBought);
                    }
                    if(saleMonth.equals("10")){
                        totalBought = totalBought + sales.getUnits();
                        yearObject.set(9,totalBought);
                    }
                    if(saleMonth.equals("11")){
                        totalBought = totalBought + sales.getUnits();
                        yearObject.set(10,totalBought);
                    }
                    if(saleMonth.equals("12")){
                        totalBought = totalBought + sales.getUnits();
                        yearObject.set(11,totalBought);
                    }
                }
            }

        }

        if( type == 0 || type == 1) {
            Double totalRevenue = 0.0;
            Double totalSold = 0.0;
            for (Sales sales : sellerSalesList) {
                String saleDate = yearFormat.format(sales.getDate());
                String saleYear = Integer.toString(calendar.get(Calendar.YEAR));



                if (saleDate.equals(year)) {
                    String saleMonth = monthFormat.format(sales.getDate());
                    if (saleMonth.equals("01")) {
                        if (type == 0) {
                            totalRevenue = totalRevenue + (sales.getUnits() * sales.getPrice());
                            yearObject.set(0, totalRevenue);
                        }
                        if (type == 1) {
                            totalSold = totalSold + (sales.getUnits());
                            yearObject.set(0, totalSold);
                        }
                    }
                    if (saleMonth.equals("02")) {
                        if (type == 0) {
                            totalRevenue = totalRevenue + (sales.getUnits() * sales.getPrice());
                            yearObject.set(1, totalRevenue);
                        }
                        if (type == 1) {
                            totalSold = totalSold + (sales.getUnits());
                            yearObject.set(1, totalSold);
                        }
                    }
                    if (saleMonth.equals("03")) {
                        if (type == 0) {
                            totalRevenue = totalRevenue + (sales.getUnits() * sales.getPrice());
                            yearObject.set(2, totalRevenue);
                        }
                        if (type == 1) {
                            totalSold = totalSold + (sales.getUnits());
                            yearObject.set(2, totalSold);
                        }
                    }
                    if (saleMonth.equals("04")) {
                        if (type == 0) {
                            totalRevenue = totalRevenue + (sales.getUnits() * sales.getPrice());
                            yearObject.set(3, totalRevenue);
                        }
                        if (type == 1) {
                            totalSold = totalSold + (sales.getUnits());
                            yearObject.set(3, totalSold);
                        }
                    }
                    if (saleMonth.equals("05")) {
                        if (type == 0) {
                            totalRevenue = totalRevenue + (sales.getUnits() * sales.getPrice());
                            yearObject.set(4, totalRevenue);
                        }
                        if (type == 1) {
                            totalSold = totalSold + (sales.getUnits());
                            yearObject.set(4, totalSold);
                        }
                    }
                    if (saleMonth.equals("06")) {
                        if (type == 0) {
                            totalRevenue = totalRevenue + (sales.getUnits() * sales.getPrice());
                            yearObject.set(5, totalRevenue);
                        }
                        if (type == 1) {
                            totalSold = totalSold + (sales.getUnits());
                            yearObject.set(5, totalSold);
                        }
                    }
                    if (saleMonth.equals("07")) {
                        if (type == 0) {
                            totalRevenue = totalRevenue + (sales.getUnits() * sales.getPrice());
                            yearObject.set(6, totalRevenue);
                        }
                        if (type == 1) {
                            totalSold = totalSold + (sales.getUnits());
                            yearObject.set(6, totalSold);
                        }
                    }
                    if (saleMonth.equals("08")) {
                        if (type == 0) {
                            totalRevenue = totalRevenue + (sales.getUnits() * sales.getPrice());
                            yearObject.set(7, totalRevenue);
                        }
                        if (type == 1) {
                            totalSold = totalSold + (sales.getUnits());
                            yearObject.set(7, totalSold);
                        }
                        System.out.println(totalRevenue);
                    }
                    if (saleMonth.equals("09")) {
                        if (type == 0) {
                            totalRevenue = totalRevenue + (sales.getUnits() * sales.getPrice());
                            yearObject.set(8, totalRevenue);
                        }
                        if (type == 1) {
                            totalSold = totalSold + (sales.getUnits());
                            yearObject.set(8, totalSold);
                        }
                    }
                    if (saleMonth.equals("10")) {
                        if (type == 0) {
                            totalRevenue = totalRevenue + (sales.getUnits() * sales.getPrice());
                            yearObject.set(9, totalRevenue);
                        }
                        if (type == 1) {
                            totalSold = totalSold + (sales.getUnits());
                            yearObject.set(9, totalSold);
                        }
                    }
                    if (saleMonth.equals("11")) {
                        if (type == 0) {
                            totalRevenue = totalRevenue + (sales.getUnits() * sales.getPrice());
                            yearObject.set(10, totalRevenue);
                        }
                        if (type == 1) {
                            totalSold = totalSold + (sales.getUnits());
                            yearObject.set(10, totalSold);

                        }
                    }
                    if (saleMonth.equals("12")) {
                        if (type == 0) {
                            totalRevenue = totalRevenue + (sales.getUnits() * sales.getPrice());
                            yearObject.set(11, totalRevenue);
                        }
                        if (type == 1) {
                            totalSold = totalSold + (sales.getUnits());
                            yearObject.set(10, totalSold);
                        }
                    }

                }
            }
        }
        barChartObject.setData(yearObject);
        return new BarChart(new ArrayList<>(Arrays.asList(barChartObject)));
    }

    public BarChartMonth getSelectedMonthStats(String userId, String year, String month, int type) {

        ///type = 0 => revenue
        ///type = 1 => sold
        ///type = 2 => bought

        /// month in format Eg "01" for January
        /// year in format Eg "1998"

        List<Sales> sellerSalesList = salesRepo.findBySellerId(userId);
        List<Sales> buyerSalesList = salesRepo.findByBuyerId(userId);

        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
        SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
        BarChartMonthObject barChartMonthObject = new BarChartMonthObject(Arrays.asList(0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0));

        List<Double> monthObject = new ArrayList<Double>(Arrays.asList(0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0));
        final List<String> bloodGroups = new ArrayList<>(Arrays.asList("A+","A-","B+","B-","AB+", "AB-", "O+","O-"));

        if(type == 2){
            Double totalBought = 0.0;
            for(Sales sales:buyerSalesList){
                String saleDate = yearFormat.format(sales.getDate());

                if (saleDate.equals(year)) {
                    String saleMonth = monthFormat.format(sales.getDate());
                    if (saleMonth.equals(month)) {
                        for (int i=0; i<bloodGroups.size();i++){
                            if(sales.getBlood_group().equals(bloodGroups.get(i))){
                                totalBought = totalBought + (sales.getUnits());
                                monthObject.set(i, totalBought);
                            }
                        }
                    }
                }
            }
        }



        if( type == 0 || type == 1) {
            Double totalRevenue = 0.0;
            Double totalSold = 0.0;
            for (Sales sales : sellerSalesList) {
                String saleDate = yearFormat.format(sales.getDate());
                if (saleDate.equals(year)) {
                    String saleMonth = monthFormat.format(sales.getDate());
                    if (saleMonth.equals(month)) {
                        if (type == 0) {
                            for (int i=0; i<bloodGroups.size();i++){
                                if(sales.getBlood_group().equals(bloodGroups.get(i))){
                                    totalRevenue = totalRevenue + (sales.getUnits() * sales.getPrice());
                                    monthObject.set(i, totalRevenue);
                                }
                            }
                        }
                        if (type == 1) {
                            for (int i=0; i<bloodGroups.size();i++){
                                if(sales.getBlood_group().equals(bloodGroups.get(i))){
                                    totalSold = totalSold + (sales.getUnits());
                                    monthObject.set(i, totalSold);
                                }
                            }
                        }
                    }

                }
            }
        }
        barChartMonthObject.setData(monthObject);
        return new BarChartMonth(new ArrayList<>(Arrays.asList(barChartMonthObject)));
    }
}
