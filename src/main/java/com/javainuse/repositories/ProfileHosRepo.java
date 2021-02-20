package com.javainuse.repositories;

import com.javainuse.models.ProfileHos;
import org.springframework.data.jpa.repository.JpaRepository;

//todo to be changed
public interface ProfileHosRepo extends JpaRepository<ProfileHos, String> {

    public ProfileHos findByEmail(String email);
    public ProfileHos findByUserId(String id);


}
