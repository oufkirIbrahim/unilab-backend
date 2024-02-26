package com.m2i.unilabmanagerbackend.service;

import com.m2i.unilabmanagerbackend.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PersonService {
    ResponseEntity<List<User>> getAllUsers();

    ResponseEntity<?> updateUser(Integer userId, User updatedUser);

    ResponseEntity<String> deleteUserById(Integer id);

    ResponseEntity<User> saveUser(User newUser);

    ResponseEntity<?> getUserById(Integer userId);

    ResponseEntity<?> getUserByEmail(String email);

    ResponseEntity<?> getUserByCin(String cin);

    ResponseEntity<?> getUserBySom(String som);


    ResponseEntity<?> getUserByFirstName(String firstname);

    ResponseEntity<?> getUserByLastName(String lastname);

    ResponseEntity<?> getUserByLabId(Integer labId);
}
