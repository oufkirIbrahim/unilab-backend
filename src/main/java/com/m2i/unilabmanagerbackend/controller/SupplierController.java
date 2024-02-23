package com.m2i.unilabmanagerbackend.controller;

import com.m2i.unilabmanagerbackend.entity.Supplier;
import com.m2i.unilabmanagerbackend.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class SupplierController {
    
    @Autowired
    private SupplierService supplierService;

    @GetMapping("/admin/suppliers")
    public ResponseEntity<List<Supplier>> getAllSuppliers(){
        return supplierService.getAllSuppliers();
    }

    @PostMapping("/admin/suppliers")
    public ResponseEntity<Supplier> saveSupplier(@RequestBody Supplier newSupplier){
        return supplierService.saveSupplier(newSupplier);
    }

    @PutMapping("/admin/suppliers/id/{id}")
    public ResponseEntity<?> updateSupplier(@PathVariable Integer id, @RequestBody Supplier updatedSupplier) {
        return supplierService.updateSupplier(id, updatedSupplier);
    }

    @DeleteMapping("/admin/suppliers/id/{id}")
    public ResponseEntity<String> deleteSupplier(@PathVariable Integer id) {
        return supplierService.deleteSupplierById(id);
    }

    @GetMapping("/admin/suppliers/id/{id}")
    public ResponseEntity<?> getSupplierById(@PathVariable Integer id) {
        return supplierService.getSupplierById(id);
    }

    @GetMapping("/admin/suppliers/email/{email}")
    public ResponseEntity<?> getSupplierByEmail(@PathVariable String email) {
        return supplierService.getSupplierByEmail(email);
    }

    @GetMapping("/admin/suppliers/name/{name}")
    public ResponseEntity<?> getSuppliersByName(@PathVariable String name) {
        return supplierService.getSuppliersByName(name);
    }
}
