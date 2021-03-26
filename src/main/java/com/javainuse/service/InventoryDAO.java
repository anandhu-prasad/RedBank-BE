package com.javainuse.service;

//import com.javainuse.analyticsModels.charts.InventoryPieChart;
import com.javainuse.config.JwtTokenUtil;
import com.javainuse.models.InventoryBb;
import com.javainuse.models.InventoryHos;
import com.javainuse.repositories.InventoryBbRepo;
import com.javainuse.repositories.InventoryHosRepo;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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

    public ResponseEntity<List<?>> extractInventory(String userId, int userType){

        try{
            HttpHeaders responseHeaders = new HttpHeaders();
            if(userType == 2 || userType == 3){
                responseHeaders.set("success", "true");
                if(userType == 3){
                    return ResponseEntity.ok().headers(responseHeaders).body(inventoryBbRepo.findByUserId(userId));
                }
                else{
                    return ResponseEntity.ok().headers(responseHeaders).body(inventoryHosRepo.findByUserId(userId));
                }
            }
            else{
                responseHeaders.set("error", "You are not authorized to view this page.");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).headers(responseHeaders).build();
            }
        }
        catch(Exception e){
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("error", "Error accessing the requests, Please try again later.");
            return ResponseEntity.notFound().headers(responseHeaders).build();
        }

    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////


    public ResponseEntity<List<InventoryHos>> updateHosInventory(String userId, int userType, List<InventoryHos> inventoryHosList){
        try{
            HttpHeaders responseHeaders = new HttpHeaders();

            if(userType == 2){
                List <InventoryHos> response = new ArrayList<>();

                for (InventoryHos inventoryHos : inventoryHosList) {
                    if(inventoryHos.getUserId().equals(userId)){
                        inventoryHosRepo.save(inventoryHos);
                        response.add(inventoryHos);
                    }
                    else{
                        responseHeaders.set("error", "You are not authorized to view this page.");
                        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).headers(responseHeaders).build();
                    }
                }

                responseHeaders.set("success", "true");
                return ResponseEntity.ok().headers(responseHeaders).body(response);
            }
            else{
                responseHeaders.set("error", "You are not authorized to perform this action.");
//                JSONObject body = new JSONObject();
//                body.put("message", "You are not authorized to perform this action.");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).headers(responseHeaders).build();
            }
        }
        catch(Exception e){
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("error", "Your inventory data cannot be fetched right now. Please try again later.");
            return ResponseEntity.notFound().headers(responseHeaders).build();
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public ResponseEntity<List<InventoryBb>> updateBbInventory(String userId, int userType, List<InventoryBb> inventoryBbList){
        try{
            HttpHeaders responseHeaders = new HttpHeaders();

            if(userType == 3){
            List <InventoryBb> response = new ArrayList<>();

            for (InventoryBb inventoryBb : inventoryBbList) {
                if(inventoryBb.getUserId().equals(userId)) {
                    inventoryBbRepo.save(inventoryBb);
                    response.add(inventoryBb);
                }
                else{
                    responseHeaders.set("error", "You are not authorized to view this page.");
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).headers(responseHeaders).build();
                }
            }

            responseHeaders.set("success", "true");
            return ResponseEntity.ok().headers(responseHeaders).body(response);

        }
            else{
            responseHeaders.set("error", "You are not authorized perform this action.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).headers(responseHeaders).build();
        }
        }
        catch(Exception e){
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("error", "Your inventory data cannot be fetched right now. Please try again later.");
            return ResponseEntity.notFound().headers(responseHeaders).build();
        }
    }

}
