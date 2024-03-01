package com.m2i.unilabmanagerbackend.repository;

import com.m2i.unilabmanagerbackend.entity.Laboratory;
import com.m2i.unilabmanagerbackend.entity.Role;
import com.m2i.unilabmanagerbackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByEmail(String email);
    User findByRole(Role role);

    Optional<User> findByEmailContaining(String email);
    Optional<User> findByCinContainingIgnoreCase(String cin);

    Optional<User> findUserBySomNumberContaining(String som);


    List<User> findAllByFirstNameContainingIgnoreCase(String firstname);
    List<User> findAllByLastNameContainingIgnoreCase(String firstname);

    List<User> findUsersByLaboratoryId(Integer labId);
}
