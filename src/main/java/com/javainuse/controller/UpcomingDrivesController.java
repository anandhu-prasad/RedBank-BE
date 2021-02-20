package com.javainuse.controller;


import com.javainuse.requests.UpcomingDrives_ReqBody;
import com.javainuse.responses.UpcomingDrives_RespBody;
import com.javainuse.service.UpcomingDrivesDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path="/upcomingdrives")
public class UpcomingDrivesController {

    @Autowired
    UpcomingDrivesDAO upcomingDrivesDAO;

    @PostMapping("/fetchdriveslist")
    public List<UpcomingDrives_RespBody> getDrivesList(@RequestBody UpcomingDrives_ReqBody data){
        return upcomingDrivesDAO.getDrives(data);
    }
}
