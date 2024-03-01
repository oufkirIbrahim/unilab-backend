package com.m2i.unilabmanagerbackend.repository;

import com.m2i.unilabmanagerbackend.entity.ConsumableOrder;
import com.m2i.unilabmanagerbackend.entity.LabMaterialOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConsumablesOrderRepository extends JpaRepository<ConsumableOrder,Integer> {

    @Query("SELECT CO FROM ConsumableOrder CO where CO.person.userId = :id")
    Optional<List<ConsumableOrder>> findConsumableOrdersByPersonId(Integer id);

    @Query("SELECT CO FROM ConsumableOrder CO where CO.person.laboratoryId= :labId")
    List<ConsumableOrder> findConsumableOrdersByLabId(Integer labId);

}
