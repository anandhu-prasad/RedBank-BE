package com.javainuse.service;


import com.javainuse.models.DonationInvitedDonors;
import com.javainuse.models.DonationRequest;
import com.javainuse.models.ProfileInd;
import com.javainuse.repositories.DonationInvitedDonorsRepo;
import com.javainuse.repositories.DonationRequestRepo;
import com.javainuse.repositories.ProfileIndRepo;
import com.javainuse.requests.FindDonors_ReqBody;
import com.javainuse.requests.FindDonors_ReqBody_withSelectedDonors;
import com.javainuse.responses.FindDonors_RespBody;
import com.javainuse.responses.SuccessResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FindDonorsDAO {

    @Autowired
    ProfileIndRepo profileIndRepo;

    @Autowired
    DonationRequestRepo donationRequestRepo;

    @Autowired
    DonationInvitedDonorsRepo donationInvitedDonorsRepo;


    public List<FindDonors_RespBody> getDonorsList(FindDonors_ReqBody data){

        List<ProfileInd> list = new ArrayList<>();
        List<FindDonors_RespBody> result = new ArrayList<>();

        list = profileIndRepo.findByBloodGroup(data.getBloodGroup());
        list = list.stream().filter( item -> item.getDonorStatus() == 1).collect(Collectors.toList());

        //change the selection criterion in the form for the front end accordingly ( either check for empty state, district fields or check for 'ALL')
        if(!(data.getState().equals("All") || data.getState().equals("") || data.getState().equals("Select state") || data.getState() == null)){
           list = list.stream().filter( item -> item.getState().equals(data.getState())).collect(Collectors.toList());
        }
        if(!(data.getDistrict().equals("All") || data.getDistrict().equals("") || data.getDistrict().equals("Select state") || data.getDistrict() == null)){
            list = list.stream().filter( item -> item.getDistrict().equals(data.getDistrict())).collect(Collectors.toList());
        }
        if(data.getPincode() != "" && data.getPincode() != null){
            list = list.stream().filter( item -> item.getPincode() == data.getPincode()).collect(Collectors.toList());
        }

        //this list will populate the find donors results table
        result = list.stream().map(item -> new FindDonors_RespBody(item.getUserId(), item.getName(), item.getAddress(),item.getState(), item.getDistrict(), item.getPincode() )).collect(Collectors.toList());
        return result;
    }


    public ResponseEntity<SuccessResponseBody> getResponse(FindDonors_ReqBody_withSelectedDonors data, String userId, Integer userType){

        //getting current timestamp
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        //saving the details of the user/recipient (who searched for donors) in the 'donation_request' table
        DonationRequest donationRequest = new DonationRequest(userId, timestamp ,data.getAddress(), data.getState(), data.getDistrict(), data.getPincode(), data.getBloodGroup(), true);
        donationRequestRepo.save(donationRequest);

        //saving the ids of the selected donors in the 'donation_invited_donor' table
        String donationId = donationRequest.getDonationId();
        List<String> idList = data.getIdList();

        for(int i = 0; i < idList.size(); i++) {
           DonationInvitedDonors obj = new DonationInvitedDonors(donationId, idList.get(i));  //2-> pending , 0-> rejected
            donationInvitedDonorsRepo.save(obj);
        }

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("success", "true");

        return ResponseEntity.ok().headers(responseHeaders).body(new SuccessResponseBody(true));
    }
}
