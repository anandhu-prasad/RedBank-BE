package com.javainuse.repositories;


import com.javainuse.model.InventoryHos;
import com.javainuse.model.InventoryPk;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryHosRepository extends JpaRepository<InventoryHos, InventoryPk> {

//    public InventoryHos findByInventoryId(InventoryId id);

//    public List<InventoryHos> findByInventoryId(InventoryId id);

//        public InventoryHos findByUserIdAndComponentAndBloodGroup(String id, String comp, String bg);

        public List<InventoryHos> findByUserId(String id);

        public InventoryHos findByUserIdAndComponent(String id, String comp);

}