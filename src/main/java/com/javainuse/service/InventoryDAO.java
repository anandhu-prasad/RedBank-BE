package com.javainuse.service;

//import com.javainuse.analyticsModels.charts.InventoryPieChart;
import com.javainuse.config.JwtTokenUtil;
import com.javainuse.models.InventoryBb;
import com.javainuse.models.InventoryHos;
import com.javainuse.repositories.InventoryBbRepo;
import com.javainuse.repositories.InventoryHosRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class InventoryDAO {

    @Autowired
    InventoryBbRepo inventoryBbRepo;

    @Autowired
    InventoryHosRepo inventoryHosRepo;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

//    public  List<InventoryPieChart> getInventoryStats(String userId, int type) {
//        List<InventoryBb> inventoryBbList = inventoryBbRepo.findByUserId(userId);
//        final List<String> bloodGroups = new ArrayList<>(Arrays.asList("A+","A-", "B+","B-","AB+", "AB-", "O+","O-"));
//        final List<String> invBloodgroups = new ArrayList<>(Arrays.asList("aPosUnits","aNegUnits","bPosUnits","bNegUnits"));
//
//
//        List<Integer> inventoryPieChart = new ArrayList<>();
//        Integer plasmaUnits= 0;
//        InventoryPieChart aPosIntem = new InventoryPieChart();
//
//
//        for(InventoryBb inventoryBb:inventoryBbList){
//            if(inventoryBb.getComponent().equals("Plasma")){
////                inventoryPieChart.set(0,inventoryBb.getaPosUnits());
////                inventoryPieChart.set(1,inventoryBb.getaNegUnits());
////                inventoryPieChart.set(2,inventoryBb.getbPosUnits());
////                inventoryPieChart.set(3,inventoryBb.getbNegUnits());
////                inventoryPieChart.set(4,inventoryBb.getAbPosUnits());
////                inventoryPieChart.set(5,inventoryBb.getAbNegUnits());
////                inventoryPieChart.set(6,inventoryBb.getoPosUnits());
////                inventoryPieChart.set(7,inventoryBb.getoNegUnits());
////                aPosIntem.set(1,inventoryBb.getaNegUnits());
//                aPosIntem.setName("A+");
//                aPosIntem.setPopulation(inventoryBb.getaPosUnits());
//                aPosIntem.setColor("red");
//                aPosIntem.getLegendFontColor("red");
//                System.out.println("ok");
//            }
//        }
////        if (inventoryBbList.)
////        List<InventoryPieChart> inventoryPieChartList = null;
////        inventoryPieChart.add(inventoryPieChart);
//
//        return new ArrayList<>(Arrays.asList(inventoryPieChart));
//    }

    public ResponseEntity<List<?>> extractInventory(String userId, int userType){

        try{
            if(userType == 2 || userType == 3){
                HttpHeaders responseHeaders = new HttpHeaders();
                responseHeaders.set("success", "true");
                if(userType == 3){
                    return ResponseEntity.ok().headers(responseHeaders).body(inventoryBbRepo.findByUserId(userId));
                }
                else{
                    System.out.print(userId);
                    return ResponseEntity.ok().headers(responseHeaders).body(inventoryHosRepo.findByUserId(userId));
                }
            }
            else{
                HttpHeaders responseHeaders = new HttpHeaders();
                responseHeaders.set("error", "You are not authorized to view this page.");
                return ResponseEntity.badRequest().headers(responseHeaders).build();
            }
        }
        catch(Exception e){
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("error", "Error accessing the requests, Please try again later.");
            return ResponseEntity.notFound().headers(responseHeaders).build();
        }

    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////


    public ResponseEntity<List<InventoryHos>> updateHosInventory(String userId, List<InventoryHos> inventoryHosList){
        try{
            List <InventoryHos> response = new ArrayList<>();

            for (InventoryHos inventoryHos : inventoryHosList) {
                InventoryHos match = inventoryHosRepo.findByUserIdAndComponent(userId, inventoryHos.getComponent());
                //TODO SET EVERY BLOOD GROUP UNITS AND STUFF HERE.
                match = inventoryHos;
                inventoryHosRepo.save(match);
                response.add(match);
            }

            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("success", "true");
            return ResponseEntity.ok().headers(responseHeaders).body(response);
        }
        catch(Exception e){
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("error", "Your inventory data cannot be fetched right now. Please try again later.");
            return ResponseEntity.notFound().headers(responseHeaders).build();
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public ResponseEntity<List<InventoryBb>> updateBbInventory(String userId, List<InventoryBb> inventoryBbList){
        try{
            List <InventoryBb> response = new ArrayList<>();

            for (InventoryBb inventoryBb : inventoryBbList) {
                //TODO SET EVERY BLOOD GROUP UNITS AND STUFF HERE.
                inventoryBbRepo.save(inventoryBb);
                response.add(inventoryBb);
            }

            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("success", "true");
            return ResponseEntity.ok().headers(responseHeaders).body(response);
        }
        catch(Exception e){
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("error", "Your inventory data cannot be fetched right now. Please try again later.");
            return ResponseEntity.notFound().headers(responseHeaders).build();
        }
    }

}
