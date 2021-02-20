package com.javainuse.repositories;

import com.javainuse.model.ProfileInd;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileIndRepository extends JpaRepository<ProfileInd, String> {
    public ProfileInd findByEmail(String email);
    public ProfileInd findByUserId(String id);

}
