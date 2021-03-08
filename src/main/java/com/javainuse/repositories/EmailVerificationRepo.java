package com.javainuse.repositories;

import com.javainuse.models.EmailVerification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailVerificationRepo extends JpaRepository<EmailVerification,String> {
    public EmailVerification findByUserEmail(String Email);
//    public EmailVerification deleteByEmail(String Email);
}
