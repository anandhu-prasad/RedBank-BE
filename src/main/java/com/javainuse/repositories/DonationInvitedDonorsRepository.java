package com.javainuse.repositories;

import com.javainuse.model.DonationInvitedDonors;
import com.javainuse.model.DonationInvitedDonorsPk;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DonationInvitedDonorsRepository extends JpaRepository<DonationInvitedDonors, DonationInvitedDonorsPk> {

//    public DonationInvitedDonors findByDonationInvitedDonorsId(DonationInvitedDonorsId did);

    public DonationInvitedDonors findByDonationIdAndUserId(String did, String uid);

    public List<DonationInvitedDonors> findByUserId(String userId);

}
