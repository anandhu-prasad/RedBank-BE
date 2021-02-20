package com.javainuse.repositories;

import com.javainuse.models.DonationInvitedDonors;
import com.javainuse.util.DonationInvitedDonorsPk;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DonationInvitedDonorsRepo extends JpaRepository<DonationInvitedDonors, DonationInvitedDonorsPk> {

//    public DonationInvitedDonors findByDonationInvitedDonorsId(DonationInvitedDonorsId did);

    public DonationInvitedDonors findByDonationIdAndUserId(String did, String uid);

    public List<DonationInvitedDonors> findByUserId(String userId);

}
