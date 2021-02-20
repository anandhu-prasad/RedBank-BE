package com.javainuse.repositories;

import com.javainuse.model.InventoryBb;
import com.javainuse.model.InventoryPk;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryBbRepository extends JpaRepository<InventoryBb, InventoryPk> {

//    public InventoryBb findByInventoryId(InventoryId id);

//    public InventoryBb findByUserIdAndComponentAndBloodGroup(String id, String comp, String bg);

    public List<InventoryBb> findByUserId(String id);

    public InventoryBb findByUserIdAndComponent(String id, String comp);


}
