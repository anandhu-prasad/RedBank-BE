package com.javainuse.service;

import com.javainuse.models.*;
import com.javainuse.repositories.*;
import com.javainuse.requests.*;
import com.javainuse.responses.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProfileDAO {

    @Autowired
    ProfileBbRepo profileBbRepo;

    @Autowired
    ProfileIndRepo profileIndRepo;

    @Autowired
    ProfileHosRepo profileHosRepo;

    @Autowired
    DrivesRepo drivesRepo;

    @Autowired
    DonationInvitedDonorsRepo donationInvitedDonorsRepo;

    @Autowired
    DriveInvitedDonorsRepo driveInvitedDonorsRepo;

    @Autowired
    DonationRequestRepo donationRequestRepo;

    @Autowired
    SalesRepo salesRepo;

    @Autowired
    Verify_ChangePasswordDAO verifyChangePasswordDAO;

    @Autowired
    NotificationRepo notificationRepo;

    public ResponseEntity<SuccessResponseBody> setDonorStatusNotification(String userId){
        try{

            Notification notification = new Notification(userId, "Eligibility update","You are now eligible to donate blood!" ,new Timestamp(System.currentTimeMillis()));
            notificationRepo.save(notification);
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("success", "true");
            return ResponseEntity.ok().headers(responseHeaders).body(new SuccessResponseBody(true));

        }
        catch (Exception e){
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("error", "Notification cannot be set right now!");
            return ResponseEntity.notFound().headers(responseHeaders).build();
        }
    }


//    last donation date - > condition - > set notification -> set donation status


    public ResponseEntity<SuccessResponseBody> changePassword(NewPassword newPassword, String userId, int userType){

        if (userType == 1) {
            return verifyChangePasswordDAO.changeIndPassword(newPassword.getNewPassword(), userId);
        } else if (userType == 3) {
            return verifyChangePasswordDAO.changeBbPassword(newPassword.getNewPassword(), userId);
        } else {
            return verifyChangePasswordDAO.changeHosPassword(newPassword.getNewPassword(), userId);
        }
    }


    public ResponseEntity<SuccessResponseBody> verifyPass(CurrentPassword currentPassword, String userId, int userType){
        if(userType == 1){
            return verifyChangePasswordDAO.verifyIndPassword(currentPassword.getCurrentPassword(), userId);
        }else if(userType == 3){
            return verifyChangePasswordDAO.verifyBbPassword(currentPassword.getCurrentPassword(), userId);
        }else{
            return verifyChangePasswordDAO.verifyHosPassword(currentPassword.getCurrentPassword(), userId);
        }
    }


    public ResponseEntity<?> getProfileDataDetails(String userId, int userType){

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("success", "true");

        if(userType == 1){
            ProfileInd obj = profileIndRepo.findByUserId(userId);
            List<DonationInvitedDonors> donationCount = donationInvitedDonorsRepo.findByUserId(userId);
            List<DriveInvitedDonors> drivesCount = driveInvitedDonorsRepo.findByUserId(userId);
            ProfileIndividualData obj1 = new ProfileIndividualData(obj.getBloodGroup(), obj.getEmail(), obj.getDob(), obj.getPhone(),
                    obj.getAddress(), obj.getState(), obj.getDistrict(),obj.getPincode(),
                    obj.getRegistration_date(),obj.getLast_donation_date(),donationCount.size(),
                    drivesCount.size()+ donationCount.size() , drivesCount.size());
            return ResponseEntity.ok().headers(responseHeaders).body(obj1);
        }
        else if(userType == 3){

            ProfileBb obj = profileBbRepo.findByUserId(userId);
            List<Drives> drivesConducted = drivesRepo.findByUserId(userId);
            List<DonationRequest> requestMade = donationRequestRepo.findByUserId(userId);
            List<Sales> sales = salesRepo.findBySellerId(userId);
            List<String> phone = new ArrayList<>();
            int totalSales= sales.size();

            if(obj.getPhone1()!= null){
                phone.add(obj.getPhone1());
            }
            if(obj.getPhone2()!= null){
                phone.add(obj.getPhone2());
            }
            if(obj.getPhone3()!= null){
                phone.add(obj.getPhone3());
            }
            if(obj.getPhone4()!= null){
                phone.add(obj.getPhone4());
            }
            if(obj.getPhone5()!= null){
                phone.add(obj.getPhone5());
            }
//            phone.addAll(Arrays.asList(obj.getPhone1(),obj.getPhone2(),obj.getPhone3(),obj.getPhone4(),obj.getPhone5()));
            ProfileBB_HosData obj1 = new ProfileBB_HosData(obj.getEmail(), obj.getLicense_number(),phone,obj.getAddress(),obj.getState(),
                    obj.getDistrict(),obj.getPincode(),obj.getRegistration_date(), drivesConducted.size(),totalSales,requestMade.size());
            return ResponseEntity.ok().headers(responseHeaders).body(obj1);
        }else{
            ProfileHos obj = profileHosRepo.findByUserId(userId);
            List<Drives> drivesConducted = drivesRepo.findByUserId(userId);
            List<DonationRequest> requestMade = donationRequestRepo.findByUserId(userId);
            List<String> phone = new ArrayList<>();
            if(obj.getPhone1()!= null){
                phone.add(obj.getPhone1());
            }
            if(obj.getPhone2()!= null){
                phone.add(obj.getPhone2());
            }
            if(obj.getPhone3()!= null){
                phone.add(obj.getPhone3());
            }
            if(obj.getPhone4()!= null){
                phone.add(obj.getPhone4());
            }
            if(obj.getPhone5()!= null){
                phone.add(obj.getPhone5());
            }
//            phone.addAll(Arrays.asList(obj.getPhone1(),obj.getPhone2(),obj.getPhone3(),obj.getPhone4(),obj.getPhone5()));
            ProfileBB_HosData obj1 = new ProfileBB_HosData(obj.getEmail(),obj.getLicense_number(), phone, obj.getAddress(),obj.getState(),
                    obj.getDistrict(), obj.getPincode(),obj.getRegistration_date(), drivesConducted.size(),0,requestMade.size());
            return ResponseEntity.ok().headers(responseHeaders).body(obj1);
        }
    }

    public ResponseEntity<?> getProfileDetails(String userId, int userType){
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("success", "true");

        if(userType == 1){
            ProfileInd obj = profileIndRepo.findByUserId(userId);


            ProfileDataInd obj1 = new ProfileDataInd(obj.getName(), obj.getUserId(), obj.getDonorStatus(), obj.getLast_donation_date());

            return ResponseEntity.ok().headers(responseHeaders).body(obj1);

        }
        else if(userType == 3){
            ProfileBb obj = profileBbRepo.findByUserId(userId);
//            List<Drives> drivesConducted = drivesRepo.findByUserId(userId);
            ProfileDataBb_Hos obj1 = new ProfileDataBb_Hos(obj.getName(), obj.getUserId());
            return ResponseEntity.ok().headers(responseHeaders).body(obj1);

        }else{
            ProfileHos obj = profileHosRepo.findByUserId(userId);
            ProfileDataBb_Hos obj1 = new ProfileDataBb_Hos(obj.getName(), obj.getUserId());
            return ResponseEntity.ok().headers(responseHeaders).body(obj1);
        }

    }

    public ResponseEntity<DonorStatusRequestBody> updateDonorStatus(DonorStatusRequestBody donorStatusRequestBody, String userId){
        try{
            ProfileInd profileInd = profileIndRepo.findByUserId(userId);

            System.out.println("Donor status to be set: " + donorStatusRequestBody.getDonorStatus());

            long lastDonated = profileInd.getLast_donation_date().getTime();
            long current = new Timestamp(System.currentTimeMillis()).getTime();

            //? FOR ANY CASE, DONOR STATUS OF AN INDIVIDUAL CAN ONLY BE CHANGED FROM 2 TO ANYTHING ONLY AFTER 55 DAYS OR MORE.
            if(profileInd.getDonorStatus() == 2 && profileInd.getLast_donation_date() != null && (current - lastDonated) / (1000 * 60 * 60 * 24) < 55) {
            //TODO brief web team on the return object change.
                HttpHeaders responseHeaders = new HttpHeaders();
                responseHeaders.set("success", "true");
                System.out.println("Donor status changed to " + donorStatusRequestBody.getDonorStatus());
                return ResponseEntity.ok().headers(responseHeaders).body(new DonorStatusRequestBody(2));
            }
            else{
                profileInd.setDonorStatus(donorStatusRequestBody.getDonorStatus());
                profileIndRepo.save(profileInd);
                HttpHeaders responseHeaders = new HttpHeaders();
                responseHeaders.set("success", "true");
                System.out.println("Donor status changed to " + donorStatusRequestBody.getDonorStatus());
                return ResponseEntity.ok().headers(responseHeaders).body(new DonorStatusRequestBody(profileInd.getDonorStatus()));
            }
        }
        catch(Exception e){
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("error", "cannot set donor status right now, please try again later.");
            return ResponseEntity.notFound().headers(responseHeaders).build();
        }
    }

    public ResponseEntity<SuccessResponseBody> updateIndProfile(ProfileUpdateIndRequestBody profile, String userId){
        try{

            ProfileInd match = profileIndRepo.findByUserId(userId);

            //? SETTING NEW VALUES OF THOSE FIELDS THAT ARE EDITABLE.
            match.setAddress(profile.getAddress());
            match.setDistrict(profile.getDistrict());
            match.setState(profile.getState());
            match.setPincode(profile.getPincode());
            match.setPhone(profile.getPhone());
            match.setBloodGroup(profile.getBloodGroup());

            profileIndRepo.save(match);

            //! SEND THE NEWLY UPDATED PROFILE DETAILS AS RESPONSE BODY IF REQUIRED.

            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("success", "true");
            return ResponseEntity.ok().headers(responseHeaders).body(new SuccessResponseBody((true)));
        }
        catch(Exception e){
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("error", "Cannot update your profile at the moment, please try again.");
            return ResponseEntity.notFound().headers(responseHeaders).build();
        }
    }

    public ResponseEntity<SuccessResponseBody> updateHosProfile(ProfileUpdateHosBbRequestBody profile, String userId){

        try{
            ProfileHos match = profileHosRepo.findByUserId(userId);

            //? SETTING NEW VALUES OF THOSE FIELDS THAT ARE EDITABLE.
            match.setAddress(profile.getAddress());
            match.setDistrict(profile.getDistrict());
            match.setState(profile.getState());
            match.setPincode(profile.getPincode());

            if(profile.getPhone().size() >= 1){
                match.setPhone1(profile.getPhone().get(0));
            }
            if(profile.getPhone().size() >= 2){
                match.setPhone2(profile.getPhone().get(1));
            }
            if(profile.getPhone().size() >= 3){
                match.setPhone3(profile.getPhone().get(2));
            }
            if(profile.getPhone().size() >= 4){
                match.setPhone4(profile.getPhone().get(3));
            }
            if(profile.getPhone().size() >= 5){
                match.setPhone5(profile.getPhone().get(4));
            }

            profileHosRepo.save(match);
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("success", "true");
            return ResponseEntity.ok().headers(responseHeaders).body(new SuccessResponseBody((true)));
        }
        catch(Exception e){
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("error", "Cannot update your profile at the moment, please try again.");
            return ResponseEntity.notFound().headers(responseHeaders).build();
        }
    }

    public ResponseEntity<SuccessResponseBody> updateBbProfile(ProfileUpdateHosBbRequestBody profile, String userId){

        try{

            ProfileBb match = profileBbRepo.findByUserId(userId);

            //? SETTING NEW VALUES OF THOSE FIELDS THAT ARE EDITABLE.
            match.setAddress(profile.getAddress());
            match.setDistrict(profile.getDistrict());
            match.setState(profile.getState());
            match.setPincode(profile.getPincode());

            if(profile.getPhone().size() >= 1){
                match.setPhone1(profile.getPhone().get(0));
            }
            if(profile.getPhone().size() >= 2){
                match.setPhone2(profile.getPhone().get(1));
            }
            if(profile.getPhone().size() >= 3){
                match.setPhone3(profile.getPhone().get(2));
            }
            if(profile.getPhone().size() >= 4){
                match.setPhone4(profile.getPhone().get(3));
            }
            if(profile.getPhone().size() >= 5){
                match.setPhone5(profile.getPhone().get(4));
            }


            profileBbRepo.save(match);
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("success", "true");
            return ResponseEntity.ok().headers(responseHeaders).body(new SuccessResponseBody((true)));

        }
        catch(Exception e){
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("error", "Cannot update your profile at the moment, please try again.");
            return ResponseEntity.notFound().headers(responseHeaders).build();
        }
    }

}
