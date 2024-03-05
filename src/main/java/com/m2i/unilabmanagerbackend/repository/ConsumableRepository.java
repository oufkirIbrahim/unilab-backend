package com.m2i.unilabmanagerbackend.repository;

import com.m2i.unilabmanagerbackend.DTO.statistics.ConsumablesData;
import com.m2i.unilabmanagerbackend.entity.Consumable;
import com.m2i.unilabmanagerbackend.entity.Laboratory;
import com.m2i.unilabmanagerbackend.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface ConsumableRepository extends JpaRepository<Consumable,Integer> {

    List<Consumable> findByTypeContainingIgnoreCase(String type);

    @Query("SELECT CO FROM Consumable CO WHERE CO.laboratory = :lab AND YEAR(CO.assignmentDate) = :year order by CO.assignmentDate")
    List<Consumable> findByLaboratoryAndYear(Laboratory lab, int year);

    List<Consumable> findByLaboratory(Laboratory laboratory);

    @Query(value = "SELECT DISTINCT type FROM consumable where laboratory_id = null", nativeQuery = true)
    List<Consumable> findDistinctType();


    Long countByLaboratoryIsNotNull();

    @Query("select distinct co.supplier from Consumable co where co.supplier != null ")
    List<Supplier> findDistinctSuppliers();

    @Query("SELECT c.type, SUM(c.quantity) FROM Consumable c GROUP BY c.type")
    List<Object[]> findDistinctConsumableQuantities();

    @Query("SELECT DISTINCT YEAR(c.assignmentDate) FROM Consumable c where c.assignmentDate != null")
    List<Integer> findDistinctYears();
}
