package com.m2i.unilabmanagerbackend.service.impl;

import com.m2i.unilabmanagerbackend.entity.Rubric;
import com.m2i.unilabmanagerbackend.entity.RubricBudget;
import com.m2i.unilabmanagerbackend.error.RubricNotFoundException;
import com.m2i.unilabmanagerbackend.repository.RubricBudgetRepository;
import com.m2i.unilabmanagerbackend.repository.RubricRepository;
import com.m2i.unilabmanagerbackend.service.RubricService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class RubricServiceImpl implements RubricService {

    @Autowired
    private RubricRepository rubricRepository;

    @Autowired
    private RubricBudgetRepository rubricBudgetRepository;

    @Override
    public Rubric saveRubric(Rubric rubric) {
        return rubricRepository.save(rubric);
    }

    @Override
    public List<Rubric> getAllRubric() {
        return rubricRepository.findAll();
    }

    @Override
    public Rubric getRubricById(Integer id) throws RubricNotFoundException {
        Optional<Rubric> rb = rubricRepository.findById(id);
        if(!rb.isPresent()) throw new RubricNotFoundException("Rubric Not found");
        return rb.get();
    }

    @Override
    public Rubric getRubricByCode(String code) throws RubricNotFoundException {
        Optional<Rubric> rb = rubricRepository.findByCode(code);
        if(!rb.isPresent()) throw new RubricNotFoundException("Rubric Not found");
        return rb.get();
    }

    public ResponseEntity<?> updateRubric(Integer id , Rubric rubric) throws RubricNotFoundException{
        try {
            Rubric rb = rubricRepository.findById(id).orElseThrow(() -> new RubricNotFoundException("Rubric not found with id: " + id));;

            Rubric updatedRubric = rubricRepository.save(updateFields(rb,rubric));
            return ResponseEntity.ok(updatedRubric);
        }catch (RubricNotFoundException e){
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Rubric not found with id: " + id);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating rubric");
        }
        return null;
    }
    public ResponseEntity<String> deleteRubricById(Integer rubricId) {
        Optional<Rubric> optionalRubric = rubricRepository.findById(rubricId);

        if (optionalRubric.isPresent()) {
            Rubric rubric = optionalRubric.get();
            rubricRepository.delete(rubric);
            return new ResponseEntity<>("Rubric deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Rubric not found with id: " + rubricId, HttpStatus.NOT_FOUND);
        }
    }

    private Rubric updateFields(Rubric existingRubric, Rubric newRubric) {
        if(newRubric.getCode() !=  null && !newRubric.getCode().isEmpty() ){
            existingRubric.setCode(newRubric.getCode());
        }
        if(newRubric.getDesignation() !=  null && !newRubric.getDesignation().isEmpty() ){
            existingRubric.setDesignation(newRubric.getDesignation());
        }

        if(newRubric.getRubricBudget() != null){
            RubricBudget rubricBudget = newRubric.getRubricBudget();
            if(rubricBudget.getYear() != null)
                existingRubric.getRubricBudget().setYear(rubricBudget.getYear());

            if(rubricBudget.getEngaged_amount() != null)
                existingRubric.getRubricBudget().setEngaged_amount(rubricBudget.getEngaged_amount());

            if(rubricBudget.getAllocated_amount() != null)
                existingRubric.getRubricBudget().setAllocated_amount(rubricBudget.getAllocated_amount());

            if(rubricBudget.getRemaining_amount() != null)
                existingRubric.getRubricBudget().setRemaining_amount(rubricBudget.getRemaining_amount());
        }
        return existingRubric;

    }

}
