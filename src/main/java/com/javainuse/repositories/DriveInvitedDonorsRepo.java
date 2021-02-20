package com.javainuse.repositories;

import com.javainuse.models.DriveInvitedDonors;
import com.javainuse.util.DriveInvitedDonorsPk;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DriveInvitedDonorsRepo extends JpaRepository<DriveInvitedDonors, DriveInvitedDonorsPk> {

    public DriveInvitedDonors findByDriveIdAndUserId(String did, String uid);

    public List<DriveInvitedDonors> findByUserId(String id);

}
