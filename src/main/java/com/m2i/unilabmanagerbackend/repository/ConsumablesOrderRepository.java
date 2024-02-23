package com.m2i.unilabmanagerbackend.repository;

import com.m2i.unilabmanagerbackend.entity.ConsumableOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConsumablesOrderRepository extends JpaRepository<ConsumableOrder,Integer> {

    @Query("SELECT C FROM ConsumableOrder C where C.person.userId = :id")
    Optional<List<ConsumableOrder>> findConsumableOrdersByPersonId(Integer id);
}
