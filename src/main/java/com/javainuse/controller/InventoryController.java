package com.javainuse.controller;

import com.javainuse.config.JwtTokenUtil;
import com.javainuse.models.InventoryBb;
import com.javainuse.models.InventoryHos;
import com.javainuse.repositories.InventoryBbRepo;
import com.javainuse.repositories.InventoryHosRepo;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    InventoryBbRepo inventoryBbRepo;

    @Autowired
    InventoryHosRepo inventoryHosRepo;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    /////////////////////////////////////////////////////////////////? ANANDHU | FOR TESTING ONLY | REMOVE IN PROD

    @GetMapping("/recievehosinventoryall")
    public List<InventoryHos> extractHosInventoryAll(){
        return inventoryHosRepo.findAll();
    }

    @GetMapping("/recievebbinventoryall")
    public List<InventoryBb> extractBbInventoryAll(){
        return inventoryBbRepo.findAll();
    }


    //?////////////////////////////////////////////////////////////////////////////////////////////////////////////


    //? TO GET THE INVENTORY DATA OF CURRENT USER / HOSPITAL
    //! TESTED
    @GetMapping("/receieveinventory")
    public ResponseEntity<List<?>> extractInventory(@RequestHeader ("Authorization") String userToken){
        try{
            //! HARDCODED DATA HERE, NEED TO BE EXTRACTED FROM THE TOKEN ITSELF.

            Claims claims = jwtTokenUtil.getAllClaimsFromToken(userToken.substring(7));

            String userId = claims.get("userId").toString();
            int userType = Integer.parseInt(claims.get("userType").toString());

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

    //? TO UPDATE THE INVENTORY OF CURRENT USER / HOSPITAL.
    //! TESTED

    @PutMapping("/updatehosinventory")
    public ResponseEntity<List<?>> updateHosInventory(@RequestBody List<InventoryHos> inventoryHosList, @RequestHeader ("Authorization") String userToken){

        try{

            //! HARDCODED DATA HERE, NEED TO BE EXTRACTED FROM THE TOKEN ITSELF.
            Claims claims = jwtTokenUtil.getAllClaimsFromToken(userToken.substring(7));

            String userId = claims.get("userId").toString();
            int userType = Integer.parseInt(claims.get("userType").toString());

//            if(userType == 2){
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
//            }
        }
        catch(Exception e){
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("error", "Your inventory data cannot be fetched right now. Please try again later.");
            return ResponseEntity.notFound().headers(responseHeaders).build();
        }

    }


    //? TO UPDATE THE INVENTORY OF CURRENT USER / BLOOD BANK.

    @PutMapping("/updatebbinventory")
    public ResponseEntity<List<InventoryBb>> updateBbInventory(@RequestBody List<InventoryBb> inventoryBbList, @RequestHeader ("Authorization") String userToken){

        try{

            //! HARDCODED DATA HERE, NEED TO BE EXTRACTED FROM THE TOKEN ITSELF - DONE.
            Claims claims = jwtTokenUtil.getAllClaimsFromToken(userToken.substring(7));

            String userId = claims.get("userId").toString();
            int userType = Integer.parseInt(claims.get("userType").toString());

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
