package com.javainuse.repositories;

import com.javainuse.model.ProfileBb;
import com.javainuse.model.ProfileInd;
import org.springframework.data.jpa.repository.JpaRepository;

//todo to be changed
public interface ProfileBbRepository extends JpaRepository<ProfileBb, String> {

    public ProfileBb findByEmail(String email);
    public ProfileBb findByUserId(String id);


}
