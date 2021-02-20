package com.javainuse.repositories;

import com.javainuse.model.DriveInvitedDonors;
import com.javainuse.model.DriveInvitedDonorsPk;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DriveInvitedDonorsRepository extends JpaRepository<DriveInvitedDonors, DriveInvitedDonorsPk> {

    public DriveInvitedDonors findByDriveIdAndUserId(String did, String uid);

    public List<DriveInvitedDonors> findByUserId(String id);

}
