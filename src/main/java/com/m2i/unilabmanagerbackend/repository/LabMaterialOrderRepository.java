package com.m2i.unilabmanagerbackend.repository;

import com.m2i.unilabmanagerbackend.entity.LabMaterialOrder;
import com.m2i.unilabmanagerbackend.entity.MaterialOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LabMaterialOrderRepository extends JpaRepository<LabMaterialOrder,Integer> {
    @Query("SELECT LO FROM LabMaterialOrder LO where LO.laboratory.labId  = :id")
    Optional<List<LabMaterialOrder>> findMaterialsOrdersByLabId(Integer id);

}
