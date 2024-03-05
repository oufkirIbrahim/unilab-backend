package com.m2i.unilabmanagerbackend.repository;

import com.m2i.unilabmanagerbackend.entity.Consumable;
import com.m2i.unilabmanagerbackend.entity.LabConsumableOrder;
import com.m2i.unilabmanagerbackend.entity.LabMaterialOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LabConsumablesOrderRepository extends JpaRepository<LabConsumableOrder,Integer> {

    @Query("SELECT LO FROM LabConsumableOrder LO where LO.laboratory.laboratoryId  = :id")
    List<LabConsumableOrder> findConsumablesOrdersByLabId(Integer id);


    Integer countConsumablesOrderByConsumable(Consumable consumable);
}
