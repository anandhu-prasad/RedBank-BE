package com.javainuse.repositories;

import com.javainuse.models.ProfileBb;
import com.javainuse.models.ProfileHos;
import com.javainuse.models.ProfileInd;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<ProfileInd, String> {

    ProfileInd findByUserName(String username);
}
