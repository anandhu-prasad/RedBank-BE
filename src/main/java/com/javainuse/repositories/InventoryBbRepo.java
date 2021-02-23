package com.javainuse.repositories;

import com.javainuse.models.InventoryBb;
import com.javainuse.util.InventoryPk;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryBbRepo extends JpaRepository<InventoryBb, InventoryPk> {

//    public InventoryBb findByInventoryId(InventoryId id);

//    public InventoryBb findByUserIdAndComponentAndBloodGroup(String id, String comp, String bg);

    public List<InventoryBb> findByUserId(String id);

    public InventoryBb findByUserIdAndComponent(String id, String comp);


}
