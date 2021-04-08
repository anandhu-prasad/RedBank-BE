package com.javainuse.repositories;

import com.javainuse.models.ProfileInd;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProfileIndRepo extends JpaRepository<ProfileInd, String> {

    public ProfileInd findByEmail(String email);
    public ProfileInd findByUserId(String id);
    public List<ProfileInd> findByBloodGroupIn(List<String> bloodGroups);
    public List<ProfileInd> findByBloodGroup(String bloodGroup);



}
