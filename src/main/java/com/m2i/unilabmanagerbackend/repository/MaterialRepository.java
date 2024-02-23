package com.m2i.unilabmanagerbackend.repository;

import com.m2i.unilabmanagerbackend.entity.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MaterialRepository extends JpaRepository<Material,Integer> {

    List<Material> findByInventoryNumberContainingIgnoreCase(String inv);

    List<Material> findByTypeContainingIgnoreCase(String type);
}
