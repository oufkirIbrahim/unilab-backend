package com.m2i.unilabmanagerbackend.service;

import com.m2i.unilabmanagerbackend.entity.Material;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MaterialService {
    ResponseEntity<List<Material>> getAllMaterials();

    ResponseEntity<Material> saveMaterial(Material newMaterial);

    ResponseEntity<?> updateMaterial(Integer id, Material updatedMaterial);

    ResponseEntity<String> deleteMaterialById(Integer id);

    ResponseEntity<?> getMaterialById(Integer id);

    ResponseEntity<?> getMaterialByInvNum(String inv);

    ResponseEntity<?> getMaterialsByType(String type);
}
