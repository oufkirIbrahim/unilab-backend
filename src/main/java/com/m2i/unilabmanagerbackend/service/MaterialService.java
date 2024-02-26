package com.m2i.unilabmanagerbackend.service;

import com.m2i.unilabmanagerbackend.entity.Material;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;

public interface MaterialService {
    ResponseEntity<List<Material>> getAllMaterials();

    ResponseEntity<Material> saveMaterial(Material newMaterial);

    ResponseEntity<?> updateMaterial(Integer id, Material updatedMaterial);

    ResponseEntity<String> deleteMaterialById(Integer id);

    ResponseEntity<?> getMaterialById(Integer id);

    ResponseEntity<?> getMaterialByInvNum(String inv);

    ResponseEntity<?> getMaterialsByType(String type);
    ResponseEntity<?> getMaterialsByLabId(Integer labId);

    ResponseEntity<?> getMaterialsByUserId(Integer userId);

    void exportAssignmentsPdf(HttpServletResponse response) throws JRException, IOException;


    public void exportLabMaterials(HttpServletResponse response,Integer labId,Integer year) throws JRException, IOException;


    ResponseEntity<?> assignMaterialToLab(Integer materialId, Integer labId);

    ResponseEntity<?> assignMaterialToPerson(Integer materialId, Integer userId);


}
