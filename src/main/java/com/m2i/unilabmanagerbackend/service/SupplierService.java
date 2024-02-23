package com.m2i.unilabmanagerbackend.service;

import com.m2i.unilabmanagerbackend.entity.Supplier;
import com.m2i.unilabmanagerbackend.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SupplierService {

    ResponseEntity<List<Supplier>> getAllSuppliers();
    ResponseEntity<Supplier> saveSupplier(Supplier newUser);

    ResponseEntity<?> updateSupplier(Integer id, Supplier updatedUser);


    ResponseEntity<String> deleteSupplierById(Integer id);

    ResponseEntity<?> getSupplierById(Integer id);

    ResponseEntity<?> getSupplierByEmail(String email);

    public ResponseEntity<?> getSuppliersByName(String name);
}
