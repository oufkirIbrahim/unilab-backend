package com.m2i.unilabmanagerbackend.repository;

import com.m2i.unilabmanagerbackend.entity.ConsumableAssignment;
import com.m2i.unilabmanagerbackend.entity.Laboratory;
import com.m2i.unilabmanagerbackend.entity.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MaterialRepository extends JpaRepository<Material,Integer> {

    List<Material> findByInventoryNumberContainingIgnoreCase(String inv);

    List<Material> findByTypeContainingIgnoreCase(String type);

    List<Material> findByResponsiblePersonIsNotNull();

    @Query("SELECT ma FROM Material ma WHERE ma.laboratory = :lab AND YEAR(ma.labAssignmentDate) = :year order by ma.assignmentDate")
    List<Material> findByLaboratoryAndYear(Laboratory lab, int year);
}
