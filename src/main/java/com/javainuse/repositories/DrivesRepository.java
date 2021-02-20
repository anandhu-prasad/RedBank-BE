package com.javainuse.repositories;

import com.javainuse.model.Drives;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrivesRepository extends JpaRepository<Drives, String> {

    public Drives findByDriveId(String did);

}
