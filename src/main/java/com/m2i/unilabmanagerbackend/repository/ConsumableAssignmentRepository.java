package com.m2i.unilabmanagerbackend.repository;

import com.m2i.unilabmanagerbackend.entity.ConsumableAssignment;
import com.m2i.unilabmanagerbackend.entity.ConsumableAssignmentKey;
import com.m2i.unilabmanagerbackend.entity.Laboratory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsumableAssignmentRepository extends JpaRepository<ConsumableAssignment, ConsumableAssignmentKey> {

    @Query("SELECT ca FROM ConsumableAssignment ca WHERE ca.laboratory = :lab AND YEAR(ca.assignmentDate) = :year order by ca.assignmentDate")
    List<ConsumableAssignment> findByLaboratoryAndYear(Laboratory lab, int year);

    List<ConsumableAssignment> findByLaboratory(Laboratory laboratory);
}
