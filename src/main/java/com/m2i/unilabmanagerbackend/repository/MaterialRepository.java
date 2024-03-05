package com.m2i.unilabmanagerbackend.repository;


import com.m2i.unilabmanagerbackend.entity.Laboratory;
import com.m2i.unilabmanagerbackend.entity.Material;
import com.m2i.unilabmanagerbackend.entity.Supplier;
import com.m2i.unilabmanagerbackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MaterialRepository extends JpaRepository<Material,Integer> {

    List<Material> findByInventoryNumberContainingIgnoreCase(String inv);

    List<Material> findByTypeContainingIgnoreCase(String type);

    List<Material> findByResponsiblePersonIsNotNull();

    @Query("SELECT ma FROM Material ma WHERE ma.laboratory = :lab AND YEAR(ma.labAssignmentDate) = :year order by ma.assignmentDate")
    List<Material> findByLaboratoryAndYear(Laboratory lab, int year);

    List<Material> findByLaboratory(Laboratory laboratory);

    List<Material> findByResponsiblePerson(User user);

    Long countByResponsiblePersonIsNotNull();

    @Query("select distinct ma.supplier from Material ma where ma.supplier != null ")
    List<Supplier> findDistinctSuppliers();

    @Query("SELECT DISTINCT YEAR(m.labAssignmentDate) FROM Material m where m.labAssignmentDate != null")
    List<Integer> findDistinctYears();

}
