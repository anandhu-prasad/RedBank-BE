package com.javainuse.repositories;

import com.javainuse.models.ProfileBb;
import org.springframework.data.jpa.repository.JpaRepository;

//todo to be changed
public interface ProfileBbRepo extends JpaRepository<ProfileBb, String> {

    public ProfileBb findByEmail(String email);
    public ProfileBb findByUserId(String id);


}
