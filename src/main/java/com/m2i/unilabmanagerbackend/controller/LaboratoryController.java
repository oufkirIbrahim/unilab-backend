package com.m2i.unilabmanagerbackend.controller;

import com.m2i.unilabmanagerbackend.entity.Laboratory;
import com.m2i.unilabmanagerbackend.service.LaboratoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LaboratoryController {

    @Autowired
    private LaboratoryService laboratoryService;

    @GetMapping("/admin/laboratories")
    public ResponseEntity<List<Laboratory>> getAllLaboratories(){
        return laboratoryService.getAllLaboratories();
    }

    @PostMapping("/admin/laboratories")
    public ResponseEntity<Laboratory> saveLaboratory(@RequestBody Laboratory newLaboratory){
        return laboratoryService.saveLaboratory(newLaboratory);
    }

    @PutMapping("/admin/laboratories/id/{id}")
    public ResponseEntity<?> updateLaboratory(@PathVariable Integer id, @RequestBody Laboratory updatedLaboratory) {
        return laboratoryService.updateLaboratory(id, updatedLaboratory);
    }

    @DeleteMapping("/admin/laboratories/id/{id}")
    public ResponseEntity<String> deleteLaboratory(@PathVariable Integer id) {
        return laboratoryService.deleteLaboratoryById(id);
    }

    @GetMapping("/admin/laboratories/id/{id}")
    public ResponseEntity<?> getLaboratoryById(@PathVariable Integer id) {
        return laboratoryService.getLaboratoryById(id);
    }

    @GetMapping("/admin/laboratories/name/{name}")
    public ResponseEntity<?> getLaboratoryByName(@PathVariable String name) {
        return laboratoryService.getLaboratoryByName(name);
    }

}
