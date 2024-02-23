package com.m2i.unilabmanagerbackend.service;

import com.m2i.unilabmanagerbackend.entity.Laboratory;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface LaboratoryService {
    ResponseEntity<List<Laboratory>> getAllLaboratories();

    ResponseEntity<Laboratory> saveLaboratory(Laboratory newLaboratory);

    ResponseEntity<?> updateLaboratory(Integer id, Laboratory updatedLaboratory);

    ResponseEntity<String> deleteLaboratoryById(Integer id);

    ResponseEntity<?> getLaboratoryById(Integer id);

    ResponseEntity<?> getLaboratoryByName(String name);
}
