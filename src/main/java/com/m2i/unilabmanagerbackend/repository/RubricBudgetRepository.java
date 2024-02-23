package com.m2i.unilabmanagerbackend.repository;

import com.m2i.unilabmanagerbackend.entity.RubricBudget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
public interface RubricBudgetRepository extends JpaRepository<RubricBudget,Integer> {
}
