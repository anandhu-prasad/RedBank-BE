package com.javainuse.repositories;

import com.javainuse.models.EmailOtpMapping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OtpRepo extends JpaRepository<EmailOtpMapping, String> {
    public EmailOtpMapping findByUserEmail(String userEmail);
}
