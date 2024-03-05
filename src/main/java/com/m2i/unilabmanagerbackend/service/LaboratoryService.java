package com.m2i.unilabmanagerbackend.service;

import com.m2i.unilabmanagerbackend.entity.Laboratory;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;

public interface LaboratoryService {
    ResponseEntity<List<Laboratory>> getAllLaboratories();

    ResponseEntity<Laboratory> saveLaboratory(Laboratory newLaboratory);

    ResponseEntity<?> updateLaboratory(Integer id, Laboratory updatedLaboratory);

    ResponseEntity<String> deleteLaboratoryById(Integer id);

    ResponseEntity<?> getLaboratoryById(Integer id);

    ResponseEntity<?> getLaboratoryByName(String name);

    public void exportLabDetails(HttpServletResponse response, Integer labId) throws JRException, IOException;

    ResponseEntity<?> getLaboratoryByResponsibleId(Integer id);
}
