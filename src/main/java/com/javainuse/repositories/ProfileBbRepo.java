package com.javainuse.repositories;

import com.javainuse.models.ProfileBb;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//todo to be changed
public interface ProfileBbRepo extends JpaRepository<ProfileBb, String> {

    public ProfileBb findByEmail(String email);
    public ProfileBb findByUserId(String id);


    List<ProfileBb> findByPincode(String pincode);

    List<ProfileBb> findByState(String state);

    List<ProfileBb> findByStateAndPincode(String state, String pincode);

    


    List<ProfileBb> findByStateAndDistrictAndPincode(String state, String district, String pincode);

    List<ProfileBb> findByStateAndDistrict(String state, String district);

    List<ProfileBb> findByDistrictAndPincode(String district, String pincode);
}
