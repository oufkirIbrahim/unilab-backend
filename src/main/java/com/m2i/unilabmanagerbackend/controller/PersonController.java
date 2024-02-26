package com.m2i.unilabmanagerbackend.controller;

import com.m2i.unilabmanagerbackend.entity.User;
import com.m2i.unilabmanagerbackend.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("/admin/persons")
    public ResponseEntity<List<User>> getAllUsers(){
        return personService.getAllUsers();
    }

    @PostMapping ("/admin/persons")
    public ResponseEntity<User> saveUser(@RequestBody User newUser){
        return personService.saveUser(newUser);
    }

    @PutMapping("/admin/persons/id/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Integer id, @RequestBody User updatedUser) {
        return personService.updateUser(id, updatedUser);
    }

    @DeleteMapping("/admin/persons/id/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer id) {
        return personService.deleteUserById(id);
    }

    @GetMapping({"/admin/persons/id/{id}", "/responsible/persons/id/{id}", "/user/persons/id/{id}"})
    public ResponseEntity<?> getUserById(@PathVariable Integer id) {
        return personService.getUserById(id);
    }

    @GetMapping("/admin/persons/email/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email) {
        return personService.getUserByEmail(email);
    }

    @GetMapping("/admin/persons/cin/{cin}")
    public ResponseEntity<?> getUserByCin(@PathVariable String cin) {
        return personService.getUserByCin(cin);
    }

    @GetMapping("/admin/persons/som/{som}")
    public ResponseEntity<?> getUserBySom(@PathVariable String som) {
        return personService.getUserBySom(som);
    }

    @GetMapping("/admin/persons/firstname/{firstname}")
    public ResponseEntity<?> getUserByFirstName(@PathVariable String firstname) {
        return personService.getUserByFirstName(firstname);
    }

    @GetMapping("/admin/persons/lastname/{lastname}")
    public ResponseEntity<?> getUserByLastname(@PathVariable String lastname) {
        return personService.getUserByLastName(lastname);
    }

    @GetMapping("/responsible/persons/labId/{labId}")
    public ResponseEntity<?> getUserByLabId(@PathVariable Integer labId) {
        return personService.getUserByLabId(labId);
    }
}
