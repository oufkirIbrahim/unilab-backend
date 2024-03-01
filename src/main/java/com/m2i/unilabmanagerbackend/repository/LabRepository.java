package com.m2i.unilabmanagerbackend.repository;

import com.m2i.unilabmanagerbackend.entity.Laboratory;
import com.m2i.unilabmanagerbackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LabRepository extends JpaRepository<Laboratory,Integer> {

    List<Laboratory> findByNameContainingIgnoreCase(String name);

    Optional<Laboratory> findByResponsiblePersonUserId(Integer userId);
    Optional<Laboratory> findByDeputyPersonUserId(Integer userId);
}
