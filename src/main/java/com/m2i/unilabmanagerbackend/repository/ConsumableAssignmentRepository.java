package com.m2i.unilabmanagerbackend.repository;

import com.m2i.unilabmanagerbackend.entity.ConsumableAssignment;
import com.m2i.unilabmanagerbackend.entity.ConsumableAssignmentKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsumableAssignmentRepository extends JpaRepository<ConsumableAssignment, ConsumableAssignmentKey> {
}
