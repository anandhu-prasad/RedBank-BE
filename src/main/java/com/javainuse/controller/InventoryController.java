package com.javainuse.controller;

import com.javainuse.config.JwtTokenUtil;
import com.javainuse.model.InventoryBb;
import com.javainuse.model.InventoryHos;
import com.javainuse.repositories.InventoryBbRepository;
import com.javainuse.repositories.InventoryHosRepository;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    InventoryBbRepository inventoryBbRepository;

    @Autowired
    InventoryHosRepository inventoryHosRepository;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    /////////////////////////////////////////////////////////////////? ANANDHU | FOR TESTING ONLY | REMOVE IN PROD

    @GetMapping("/recievehosinventoryall")
    public List<InventoryHos> extractHosInventoryAll(){
        return inventoryHosRepository.findAll();
    }

    @GetMapping("/recievebbinventoryall")
    public List<InventoryBb> extractBbInventoryAll(){
        return inventoryBbRepository.findAll();
    }


    //?////////////////////////////////////////////////////////////////////////////////////////////////////////////


    //? TO GET THE INVENTORY DATA OF CURRENT USER / HOSPITAL
    //! TESTED
    @GetMapping("/recieveinventory")
    public ResponseEntity<List<?>> extractInventory(@RequestHeader ("Authorization") String userToken){
        try{
            //! HARDCODED DATA HERE, NEED TO BE EXTRACTED FROM THE TOKEN ITSELF.

            Claims claims = jwtTokenUtil.getAllClaimsFromToken(userToken.substring(7));

            String userId = claims.get("userId").toString();
            int userType = Integer.parseInt(claims.get("userType").toString());

            if(userType == 2 || userType == 2){
                HttpHeaders responseHeaders = new HttpHeaders();
                responseHeaders.set("success", "true");
                if(userType == 3){
                    return ResponseEntity.ok().headers(responseHeaders).body(inventoryBbRepository.findByUserId(userId));
                }
                else{
                    return ResponseEntity.ok().headers(responseHeaders).body(inventoryHosRepository.findByUserId(userId));
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
                    InventoryHos match = inventoryHosRepository.findByUserIdAndComponent(userId, inventoryHos.getComponent());
                    //TODO SET EVERY BLOOD GROUP UNITS AND STUFF HERE.
                    match = inventoryHos;
                    inventoryHosRepository.save(match);
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
                inventoryBbRepository.save(inventoryBb);
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
