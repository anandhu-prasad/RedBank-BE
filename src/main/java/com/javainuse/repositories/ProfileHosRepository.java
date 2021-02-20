package com.javainuse.repositories;

import com.javainuse.model.ProfileHos;
import com.javainuse.model.ProfileInd;
import org.springframework.data.jpa.repository.JpaRepository;

//todo to be changed
public interface ProfileHosRepository extends JpaRepository<ProfileHos, String> {

    public ProfileHos findByEmail(String email);
    public ProfileHos findByUserId(String id);


}
