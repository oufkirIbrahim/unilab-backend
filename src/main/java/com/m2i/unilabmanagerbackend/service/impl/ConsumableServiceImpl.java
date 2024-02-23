package com.m2i.unilabmanagerbackend.service.impl;


import com.m2i.unilabmanagerbackend.DTO.ConsumableAssignmentDTO;
import com.m2i.unilabmanagerbackend.entity.Consumable;
import com.m2i.unilabmanagerbackend.entity.ConsumableAssignment;
import com.m2i.unilabmanagerbackend.entity.ConsumableAssignmentKey;
import com.m2i.unilabmanagerbackend.repository.ConsumableAssignmentRepository;
import com.m2i.unilabmanagerbackend.repository.ConsumableRepository;
import com.m2i.unilabmanagerbackend.repository.LabRepository;
import com.m2i.unilabmanagerbackend.service.ConsumableService;
import com.m2i.unilabmanagerbackend.utils.Util;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsumableServiceImpl implements ConsumableService {

    @Autowired
    private ConsumableRepository consumableRepository;

    @Autowired
    private LabRepository labRepository;

    @Autowired
    private ConsumableAssignmentRepository assignmentRepository;

    /**
     * Retrieves all consumables from the database.
     *
     * @return ResponseEntity with a list of consumables and HTTP status OK,
     * or HTTP status NOT_FOUND if no consumables are found.
     */
    @Override
    public ResponseEntity<List<Consumable>> getAllConsumables() {
        List<Consumable> consumables = consumableRepository.findAll();
        if (!consumables.isEmpty()) {
            return new ResponseEntity<>(consumables, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Saves a new consumable to the database.
     *
     * @param newConsumable The consumable to be saved.
     * @return ResponseEntity with the saved consumable and HTTP status CREATED.
     */
    @Override
    public ResponseEntity<Consumable> saveConsumable(Consumable newConsumable) {

        Consumable savedConsumable = consumableRepository.save(newConsumable);
        return new ResponseEntity<>(savedConsumable, HttpStatus.CREATED);
    }

    /**
     * Updates an existing consumable in the database.
     *
     * @param id                The ID of the consumable to be updated.
     * @param updatedConsumable The updated consumable data.
     * @return ResponseEntity with HTTP status OK if the update is successful,
     * or HTTP status NOT_FOUND if the consumable with the given ID is not found.
     */
    @Override
    public ResponseEntity<?> updateConsumable(Integer id, Consumable updatedConsumable) {
        Optional<Consumable> existingConsumableOptional = consumableRepository.findById(id);

        if (existingConsumableOptional.isPresent()) {
            Consumable existingConsumable = existingConsumableOptional.get();

            // Copy non-null properties from updatedConsumable to existingConsumable
            String[] nullProperties = Util.getNullPropertyNames(updatedConsumable);
            BeanUtils.copyProperties(updatedConsumable, existingConsumable, nullProperties);

            // Save the updated consumable
            Consumable savedConsumable = consumableRepository.save(existingConsumable);
            return new ResponseEntity<>(savedConsumable, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Consumable not found with id: " + id, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Deletes a consumable by its ID from the database.
     *
     * @param id The ID of the consumable to be deleted.
     * @return ResponseEntity with a success message and HTTP status OK if the deletion is successful,
     * or HTTP status NOT_FOUND if the consumable with the given ID is not found.
     */
    @Override
    public ResponseEntity<String> deleteConsumableById(Integer id) {
        Optional<Consumable> consumableOptional = consumableRepository.findById(id);

        if (consumableOptional.isPresent()) {
            consumableRepository.deleteById(id);
            return new ResponseEntity<>("Consumable deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Consumable not found with id: " + id, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Retrieves a consumable by its ID from the database.
     *
     * @param id The ID of the consumable to be retrieved.
     * @return ResponseEntity with the retrieved consumable and HTTP status OK,
     * or HTTP status NOT_FOUND if the consumable with the given ID is not found.
     */
    @Override
    public ResponseEntity<?> getConsumableById(Integer id) {
        Optional<Consumable> consumableOptional = consumableRepository.findById(id);

        if (consumableOptional.isPresent()) {
            return new ResponseEntity<>(consumableOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Consumable not found with id: " + id, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Retrieves consumables from the database based on their type.
     *
     * @param type The type of consumables to be retrieved.
     * @return ResponseEntity with a list of consumables and HTTP status OK if consumables are found,
     * or HTTP status NOT_FOUND if no consumables are found with the specified type.
     */
    @Override
    public ResponseEntity<?> getConsumablesByType(String type) {
        // Call the repository method to find consumables by type
        List<Consumable> consumables = consumableRepository.findByTypeContainingIgnoreCase(type);

        if (!consumables.isEmpty()) {
            // Return consumables and HTTP status OK if consumables are found
            return new ResponseEntity<>(consumables, HttpStatus.OK);
        } else {
            // Return a message and HTTP status NOT_FOUND if no consumables are found with the specified type
            return new ResponseEntity<>("No consumables found with type: " + type, HttpStatus.NOT_FOUND);
        }
    }


    @Override
    public ResponseEntity<ConsumableAssignment> assignConsumable(ConsumableAssignmentDTO assignmentDto) {
        ConsumableAssignment assignment = new ConsumableAssignment(
                 new ConsumableAssignmentKey(assignmentDto.getConsumableId(),assignmentDto.getLaboratoryId())
                ,assignmentDto.getQuantity()
                ,assignmentDto.getAssignmentDate()
                ,consumableRepository.getReferenceById(assignmentDto.getConsumableId())
                ,labRepository.getReferenceById(assignmentDto.getLaboratoryId())
        );
        ConsumableAssignment savesAssignment = assignmentRepository.save(assignment);
        return new ResponseEntity<>(savesAssignment, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<ConsumableAssignment>> getAssignments(ConsumableAssignment assignment) {
        List<ConsumableAssignment> assignments = assignmentRepository.findAll();
        if(!assignments.isEmpty()){
            return new ResponseEntity<>(assignments, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}