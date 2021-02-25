package com.javainuse.repositories;

import com.javainuse.models.Drives;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DrivesRepo extends JpaRepository<Drives, String> {

    public List<Drives> findByUserId(String userid);
    public List<Drives> findByStatus(Boolean status);
    public Drives findByDriveId(String did);

}
