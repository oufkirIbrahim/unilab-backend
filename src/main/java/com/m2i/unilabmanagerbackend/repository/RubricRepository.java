package com.m2i.unilabmanagerbackend.repository;

import com.m2i.unilabmanagerbackend.entity.Rubric;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RubricRepository extends JpaRepository<Rubric,Integer> {
    Optional<Rubric> findByCode(String code);

    @Query("select sum(r.allocatedAmount) from Rubric r")
    Double sumAllocatedAmount();

    @Query("select sum(r.engagedAmount) from Rubric r")
    Double sumEngagedAmount();
}
