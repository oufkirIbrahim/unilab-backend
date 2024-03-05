package com.m2i.unilabmanagerbackend.repository;

import com.m2i.unilabmanagerbackend.entity.MaterialOrder;
import com.m2i.unilabmanagerbackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MaterialOrderRepository extends JpaRepository<MaterialOrder,Integer> {

    @Query("SELECT M FROM MaterialOrder M where M.person.userId  = :id")
    Optional<List<MaterialOrder>> findMaterialsOrdersById(Integer id);

    @Query("select MO from MaterialOrder MO where MO.person.laboratoryId = :labId")
    List<MaterialOrder> findByLabId(Integer labId);
}
