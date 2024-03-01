package com.m2i.unilabmanagerbackend.service.impl;

import com.m2i.unilabmanagerbackend.entity.Laboratory;
import com.m2i.unilabmanagerbackend.entity.User;
import com.m2i.unilabmanagerbackend.repository.LabRepository;
import com.m2i.unilabmanagerbackend.repository.UserRepository;
import com.m2i.unilabmanagerbackend.service.PersonService;
import com.m2i.unilabmanagerbackend.utils.Util;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LabRepository labRepository;
    @Override
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userRepository.findAll();

        if (!users.isEmpty()) {
            return new ResponseEntity<>(users, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<User> saveUser(User newUser) {
        newUser.setPassword(
                new BCryptPasswordEncoder()
                        .encode(newUser.getPassword())
        );
        User savedUser = userRepository.save(newUser);

        if (savedUser != null) {
            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> deleteUserById(Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            System.out.println(user.toString());
            userRepository.delete(user);
            return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not found with id: " + id, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> updateUser(Integer userId, User updatedUser) {
        if (userRepository.existsById(userId)) {
            User existingUser = userRepository.findById(userId).orElse(null);


            if (existingUser != null) {
                if(!updatedUser.getPassword().isEmpty())
                    updatedUser.setPassword(
                            new BCryptPasswordEncoder()
                                    .encode(updatedUser.getPassword())
                    );
                // Copy non-null properties from updatedUser to existingUser
                BeanUtils.copyProperties(updatedUser, existingUser, Util.getNullPropertyNames(updatedUser));

                // Save the updated user
                User savedUser = userRepository.save(existingUser);
                return new ResponseEntity<>(savedUser, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>("User not found with id: " + userId, HttpStatus.NOT_FOUND);
        }
    }


    public ResponseEntity<?> getUserById(Integer userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isPresent())
            return new ResponseEntity<>(optionalUser.get(), HttpStatus.OK);
        return new ResponseEntity<>("User not found with id: " + userId, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> getUserByEmail(String email) {
        Optional<User> optionalUser = userRepository.findByEmailContaining(email);
        if(optionalUser.isPresent())
            return new ResponseEntity<>(optionalUser.get(), HttpStatus.OK);
        return new ResponseEntity<>("User not found with email: " + email, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> getUserByCin(String cin) {
        Optional<User> optionalUser = userRepository.findByCinContainingIgnoreCase(cin);
        if(optionalUser.isPresent())
            return new ResponseEntity<>(optionalUser.get(), HttpStatus.OK);
        return new ResponseEntity<>("User not found with CIN: " + cin, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> getUserBySom(String som) {
        Optional<User> optionalUser = userRepository.findUserBySomNumberContaining(som);
        if(optionalUser.isPresent())
            return new ResponseEntity<>(optionalUser.get(), HttpStatus.OK);
        return new ResponseEntity<>("User not found with SOM: " + som, HttpStatus.NOT_FOUND);
    }
    public ResponseEntity<?> getUserByFirstName(String firstname) {
        List<User> users = userRepository.findAllByFirstNameContainingIgnoreCase(firstname);
        if(!users.isEmpty())
            return new ResponseEntity<>(users, HttpStatus.OK);
        return new ResponseEntity<>("User not found ", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> getUserByLastName(String lastname) {
        List<User> users = userRepository.findAllByLastNameContainingIgnoreCase(lastname);
        if(!users.isEmpty())
            return new ResponseEntity<>(users, HttpStatus.OK);
        return new ResponseEntity<>("User not found ", HttpStatus.NOT_FOUND);
    }


    @Override
    public ResponseEntity<?> getUserByLabId(Integer labId) {
        List<User> users = userRepository.findUsersByLaboratoryId(labId);
        if(!users.isEmpty()){
            return new ResponseEntity<>(users, HttpStatus.OK);
        }
        return new ResponseEntity<>("No Users found ", HttpStatus.NOT_FOUND);
    }
}
