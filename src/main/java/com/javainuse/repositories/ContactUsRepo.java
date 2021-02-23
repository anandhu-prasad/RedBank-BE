package com.javainuse.repositories;

import com.javainuse.models.ContactUs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactUsRepo extends JpaRepository<ContactUs,String> {
    public ContactUs save(ContactUs data);
}
