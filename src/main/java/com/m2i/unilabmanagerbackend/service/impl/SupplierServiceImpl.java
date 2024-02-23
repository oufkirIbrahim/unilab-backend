package com.m2i.unilabmanagerbackend.service.impl;

import com.m2i.unilabmanagerbackend.entity.Supplier;
import com.m2i.unilabmanagerbackend.entity.Supplier;
import com.m2i.unilabmanagerbackend.entity.User;
import com.m2i.unilabmanagerbackend.repository.SupplierRepository;
import com.m2i.unilabmanagerbackend.repository.UserRepository;
import com.m2i.unilabmanagerbackend.service.SupplierService;
import com.m2i.unilabmanagerbackend.utils.Util;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierServiceImpl implements SupplierService {
    @Autowired
    private SupplierRepository SupplierRepository;

    @Autowired
    private UserRepository userRepository;
    @Override
    public ResponseEntity<List<Supplier>> getAllSuppliers() {
        List<Supplier> Suppliers = SupplierRepository.findAll();

        if (!Suppliers.isEmpty()) {
            return new ResponseEntity<>(Suppliers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Supplier> saveSupplier(Supplier newSupplier) {
        Integer respID = newSupplier.getResponsible().getUserId();
        newSupplier.setResponsible(userRepository.findById(respID).get());
        Supplier savedSupplier = SupplierRepository.save(newSupplier);

        if (savedSupplier != null) {
            return new ResponseEntity<>(savedSupplier, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> deleteSupplierById(Integer id) {
        Optional<Supplier> optionalSupplier = SupplierRepository.findById(id);

        if (optionalSupplier.isPresent()) {
            Supplier Supplier = optionalSupplier.get();
            SupplierRepository.delete(Supplier);
            return new ResponseEntity<>("Supplier deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Supplier not found with id: " + id, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> updateSupplier(Integer SupplierId, Supplier updatedSupplier) {
        if (SupplierRepository.existsById(SupplierId)) {
            Supplier existingSupplier = SupplierRepository.findById(SupplierId).orElse(null);

            if (existingSupplier != null) {
                Integer respId = updatedSupplier.getResponsible().getUserId();

                // Copy non-null properties from updatedSupplier to existingSupplier
                BeanUtils.copyProperties(updatedSupplier, existingSupplier, Util.getNullPropertyNames(updatedSupplier));
                if( respId != null) {
                    User resp = userRepository.findById(respId).get();
                    existingSupplier.setResponsible(resp);
                }
                // Save the updated Supplier
                Supplier savedSupplier = SupplierRepository.save(existingSupplier);
                return new ResponseEntity<>(savedSupplier, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>("Supplier not found with id: " + SupplierId, HttpStatus.NOT_FOUND);
        }
    }


    public ResponseEntity<?> getSupplierById(Integer SupplierId) {
        Optional<Supplier> optionalSupplier = SupplierRepository.findById(SupplierId);
        if(optionalSupplier.isPresent())
            return new ResponseEntity<>(optionalSupplier.get(), HttpStatus.OK);
        return new ResponseEntity<>("Supplier not found with id: " + SupplierId, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> getSupplierByEmail(String email) {
        Optional<Supplier> optionalSupplier = SupplierRepository.findByEmailContaining(email);
        if(optionalSupplier.isPresent())
            return new ResponseEntity<>(optionalSupplier.get(), HttpStatus.OK);
        return new ResponseEntity<>("Supplier not found with email: " + email, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> getSuppliersByName(String firstname) {
        List<Supplier> Suppliers = SupplierRepository.findAllByNameContainingIgnoreCase(firstname);
        if(!Suppliers.isEmpty())
            return new ResponseEntity<>(Suppliers, HttpStatus.OK);
        return new ResponseEntity<>("Supplier not found ", HttpStatus.NOT_FOUND);
    }
}
