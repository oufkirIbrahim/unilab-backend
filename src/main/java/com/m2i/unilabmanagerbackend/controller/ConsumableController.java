package com.m2i.unilabmanagerbackend.controller;

import com.m2i.unilabmanagerbackend.entity.Consumable;
import com.m2i.unilabmanagerbackend.service.ConsumableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class ConsumableController {

    @Autowired
    private ConsumableService consumableService;

    @GetMapping({"/admin/consumables","/responsible/consumables"})
    public ResponseEntity<List<Consumable>> getAllConsumables() {
        return consumableService.getAllConsumables();
    }

    @PostMapping("/admin/consumables")
    public ResponseEntity<Consumable> saveConsumable(@RequestBody Consumable newConsumable) {
        return consumableService.saveConsumable(newConsumable);
    }

    @PutMapping("/admin/consumables/id/{id}")
    public ResponseEntity<?> updateConsumable(@PathVariable Integer id, @RequestBody Consumable updatedConsumable) {
        return consumableService.updateConsumable(id, updatedConsumable);
    }



    @DeleteMapping("/admin/consumables/id/{id}")
    public ResponseEntity<String> deleteConsumableById(@PathVariable Integer id) {
        return consumableService.deleteConsumableById(id);
    }

    @GetMapping("/admin/consumables/id/{id}")
    public ResponseEntity<?> getConsumableById(@PathVariable Integer id) {
        return consumableService.getConsumableById(id);
    }

    @GetMapping("/admin/consumables/type/{type}")
    public ResponseEntity<?> getConsumablesByType(@PathVariable String type) {
        return consumableService.getConsumablesByType(type);
    }

//    @PostMapping("/admin/consumables/assignments")
//    public ResponseEntity<ConsumableAssignment> assignConsumable(@RequestBody ConsumableAssignmentDTO assignment) {
//        return consumableService.assignConsumable(assignment);
//    }
//
//    @GetMapping("/admin/consumables/assignments")
//    public ResponseEntity<List<ConsumableAssignment>> getAssignments() {
//        return consumableService.getAssignments();
//    }

    //get assignments by lab:
    @GetMapping({"/responsible/consumables/labId/{labId}","/user/consumables/labId/{labId}"})
    public ResponseEntity<List<Consumable>> getConsumablesByLabId(@PathVariable Integer labId) {
        return consumableService.getConsumablesByLabId(labId);
    }

//    @GetMapping("/user/consumables/labId/{userId}")
//    public ResponseEntity<List<Consumable>> getConsumablesByUserId(@PathVariable Integer userId) {
//        return consumableService.getConsumablesByUserId(userId);
//    }
}
