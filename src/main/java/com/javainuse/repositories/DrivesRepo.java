package com.javainuse.repositories;

import com.javainuse.models.Drives;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrivesRepo extends JpaRepository<Drives, String> {

    public Drives findByDriveId(String did);

}
