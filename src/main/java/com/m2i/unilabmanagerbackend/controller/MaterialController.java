package com.m2i.unilabmanagerbackend.controller;

import com.m2i.unilabmanagerbackend.entity.Material;
import com.m2i.unilabmanagerbackend.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class MaterialController {

    @Autowired
    private MaterialService materialService;

    @GetMapping({"/admin/materials","/responsible/materials"})
    public ResponseEntity<List<Material>> getAllMaterials(){
        return materialService.getAllMaterials();
    }

    @PostMapping("/admin/materials")
    public ResponseEntity<Material> saveMaterial(@RequestBody Material newMaterial){
        return materialService.saveMaterial(newMaterial);
    }

    @PutMapping("/admin/materials/id/{id}")
    public ResponseEntity<?> updateMaterial(@PathVariable Integer id, @RequestBody Material updatedMaterial) {
        return materialService.updateMaterial(id, updatedMaterial);
    }

    @DeleteMapping("/admin/materials/id/{id}")
    public ResponseEntity<String> deleteMaterial(@PathVariable Integer id) {
        return materialService.deleteMaterialById(id);
    }

    @GetMapping("/admin/materials/id/{id}")
    public ResponseEntity<?> getMaterialById(@PathVariable Integer id) {
        return materialService.getMaterialById(id);
    }

    @GetMapping("/admin/materials/inventory/{inv}")
    public ResponseEntity<?> getMaterialByInvNum(@PathVariable String inv) {
        return materialService.getMaterialByInvNum(inv);
    }

    @GetMapping("/admin/materials/type/{type}")
    public ResponseEntity<?> getMaterialByType(@PathVariable String type) {
        return materialService.getMaterialsByType(type);
    }

    //assign material to laboratory
    @PatchMapping("/admin/materials/assignments/{materialId}/{labId}")
    public ResponseEntity<?> assignMaterialToLab(@PathVariable Integer materialId, @PathVariable Integer labId) {
        return materialService.assignMaterialToLab(materialId, labId);
    }

    //assign material to person
    @PatchMapping("/responsible/materials/assignments/{materialId}/{userId}")
    public ResponseEntity<?> assignMaterialToPerson(@PathVariable Integer materialId, @PathVariable Integer userId) {
        return materialService.assignMaterialToPerson(materialId, userId);
    }

    //get material by labId
    @GetMapping({"/responsible/materials/laboratory/{labId}","/user/materials/laboratory/{labId}"})
    public ResponseEntity<?> getMaterialByLabId(@PathVariable Integer labId) {
        return materialService.getMaterialsByLabId(labId);
    }
    //get material by userId

    @GetMapping("/user/materials/userId/{userId}")
    public ResponseEntity<?> getMaterialByUserId(@PathVariable Integer userId) {
        return materialService.getMaterialsByUserId(userId);
    }

}
