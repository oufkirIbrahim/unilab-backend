package com.m2i.unilabmanagerbackend.service;

import com.m2i.unilabmanagerbackend.DTO.ConsumableAssignmentDTO;
import com.m2i.unilabmanagerbackend.entity.Consumable;
import com.m2i.unilabmanagerbackend.entity.ConsumableAssignment;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;

public interface ConsumableService {

    ResponseEntity<List<Consumable>> getAllConsumables();

    ResponseEntity<Consumable> saveConsumable(Consumable newConsumable);

    ResponseEntity<?> updateConsumable(Integer id, Consumable updatedConsumable);

    ResponseEntity<String> deleteConsumableById(Integer id);

    ResponseEntity<?> getConsumableById(Integer id);

    ResponseEntity<?> getConsumablesByType(String type);

    ResponseEntity<ConsumableAssignment> assignConsumable(ConsumableAssignmentDTO assignmentDto);

    ResponseEntity<List<ConsumableAssignment>> getAssignments(ConsumableAssignment assignment);

    void exportLabConsumables(HttpServletResponse response, Integer labId, Integer year) throws JRException, IOException;


}
