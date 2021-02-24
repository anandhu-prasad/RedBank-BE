package com.javainuse.controller;


import com.javainuse.requests.UpcomingDrives_ReqBody;
import com.javainuse.responses.UpcomingDrives_RespBody;
import com.javainuse.service.UpcomingDrivesDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path="/upcomingdrives")
public class UpcomingDrivesController {

    @Autowired
    UpcomingDrivesDAO upcomingDrivesDAO;

    @PostMapping("/fetchdriveslist")
    public ResponseEntity<List<UpcomingDrives_RespBody>> getDrivesList(@RequestBody UpcomingDrives_ReqBody data){
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("success", "true");
        return ResponseEntity.ok().headers(responseHeaders).body(upcomingDrivesDAO.getDrives(data));
    }
}
