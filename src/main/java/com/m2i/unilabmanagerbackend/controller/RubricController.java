package com.m2i.unilabmanagerbackend.controller;

import com.m2i.unilabmanagerbackend.entity.Rubric;
import com.m2i.unilabmanagerbackend.error.RubricNotFoundException;
import com.m2i.unilabmanagerbackend.service.RubricService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class RubricController {

    @Autowired
    private RubricService rubricService;

    @PostMapping
    public Rubric saveRubric(@Valid @RequestBody Rubric rubric){
        return rubricService.saveRubric(rubric);
    }


//    public List<Rubric> getAllRubric(){
//        return rubricService.getAllRubric();
//    }

    @GetMapping("/admin/rubrics")
    public List<Rubric> getAllRubric(){
        return rubricService.getAllRubric();
    }

    @GetMapping("/admin/rubric/id/{id}")
    public Rubric getRubricById(@PathVariable("id") Integer id) throws RubricNotFoundException {
        return rubricService.getRubricById(id);
    }

    @GetMapping("/admin/rubric/code/{code}")
    public Rubric getRubricByCode(@PathVariable("code") String code) throws RubricNotFoundException{
        return rubricService.getRubricByCode(code);
    }

    @PutMapping("/admin/rubric/id/{id}")
    public ResponseEntity<?> updateRubric(@PathVariable Integer id, @RequestBody Rubric rubric) throws RubricNotFoundException {
        try {
            ResponseEntity<?> updatedRubric = rubricService.updateRubric(id, rubric);
            return ResponseEntity.ok(updatedRubric);
        } catch (RubricNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating department");
        }
    }

    @DeleteMapping("/admin/rubric/id/{id}")
    public ResponseEntity<?> deleteRubric(@PathVariable Integer id){
        return rubricService.deleteRubricById(id);

    }

}
