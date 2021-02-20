package com.javainuse.repositories;

import com.javainuse.model.DonationRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DonationRequestRepository extends JpaRepository<DonationRequest, String> {

    public DonationRequest findByDonationId(String id);

    public List<DonationRequest> findByUserId(String id);

    public List<DonationRequest> findByBloodGroup(String bg);

}
