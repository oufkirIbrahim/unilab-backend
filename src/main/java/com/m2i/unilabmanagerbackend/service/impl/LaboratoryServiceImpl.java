package com.m2i.unilabmanagerbackend.service.impl;

import com.m2i.unilabmanagerbackend.entity.Laboratory;
import com.m2i.unilabmanagerbackend.entity.User;
import com.m2i.unilabmanagerbackend.repository.LabRepository;
import com.m2i.unilabmanagerbackend.repository.UserRepository;
import com.m2i.unilabmanagerbackend.service.LaboratoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LaboratoryServiceImpl implements LaboratoryService {

    @Autowired
    private LabRepository labRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * Retrieves all laboratories from the database.
     *
     * @return ResponseEntity with a list of laboratories and HTTP status OK.
     */
    @Override
    public ResponseEntity<List<Laboratory>> getAllLaboratories() {
        List<Laboratory> laboratories = labRepository.findAll();
        if(!laboratories.isEmpty())
            return new ResponseEntity<>(laboratories, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Saves a new laboratory to the database.
     *
     * @param newLaboratory The laboratory to be saved.
     * @return ResponseEntity with the saved laboratory and HTTP status CREATED.
     */
    @Override
    public ResponseEntity<Laboratory> saveLaboratory(Laboratory newLaboratory) {
        Integer respId = newLaboratory.getResponsiblePerson().getUserId();
        Integer deptId = newLaboratory.getDeputyPerson().getUserId();
        if(respId != null)
            newLaboratory.setResponsiblePerson(userRepository.findById(respId).get());
        if(deptId != null)
            newLaboratory.setDeputyPerson(userRepository.findById(deptId).get());
        Laboratory savedLaboratory = labRepository.save(newLaboratory);
        return new ResponseEntity<>(savedLaboratory, HttpStatus.CREATED);
    }

    /**
     * Updates an existing laboratory in the database.
     *
     * @param id              The ID of the laboratory to be updated.
     * @param updatedLaboratory The updated laboratory data.
     * @return ResponseEntity with HTTP status OK if the update is successful,
     *         or HTTP status NOT_FOUND if the laboratory with the given ID is not found.
     */
    @Override
    public ResponseEntity<?> updateLaboratory(Integer id, Laboratory updatedLaboratory) {
        Optional<Laboratory> existingLaboratoryOptional = labRepository.findById(id);

        if (existingLaboratoryOptional.isPresent()) {

            Integer respId = updatedLaboratory.getResponsiblePerson().getUserId();
            Integer deptId = updatedLaboratory.getDeputyPerson().getUserId();

            Laboratory existingLaboratory = existingLaboratoryOptional.get();

            if(respId != null)
                existingLaboratory.setResponsiblePerson(userRepository.findById(respId).get());
            if(deptId != null)
                existingLaboratory.setDeputyPerson(userRepository.findById(deptId).get());

            if(updatedLaboratory.getName() != null)
                existingLaboratory.setName(updatedLaboratory.getName());


            labRepository.save(existingLaboratory);
            return new ResponseEntity<>(existingLaboratory,HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Laboratory not found with id: " + id, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Deletes a laboratory by its ID from the database.
     *
     * @param id The ID of the laboratory to be deleted.
     * @return ResponseEntity with a success message and HTTP status OK if the deletion is successful,
     *         or HTTP status NOT_FOUND if the laboratory with the given ID is not found.
     */
    @Override
    public ResponseEntity<String> deleteLaboratoryById(Integer id) {
        Optional<Laboratory> laboratoryOptional = labRepository.findById(id);

        if (laboratoryOptional.isPresent()) {
            labRepository.deleteById(id);
            return new ResponseEntity<>("Laboratory deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Laboratory not found with id: " + id, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Retrieves a laboratory by its ID from the database.
     *
     * @param id The ID of the laboratory to be retrieved.
     * @return ResponseEntity with the retrieved laboratory and HTTP status OK,
     *         or HTTP status NOT_FOUND if the laboratory with the given ID is not found.
     */
    @Override
    public ResponseEntity<?> getLaboratoryById(Integer id) {
        Optional<Laboratory> laboratoryOptional = labRepository.findById(id);

        if (laboratoryOptional.isPresent()) {
            return new ResponseEntity<>(laboratoryOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Laboratory not found with id: " + id, HttpStatus.NOT_FOUND);
        }
    }


    /**
     * Retrieves a laboratory by its name from the database.
     *
     * @param name The name of the laboratory to be retrieved.
     * @return ResponseEntity with the retrieved laboratory and HTTP status OK,
     *         or HTTP status NOT_FOUND if the laboratory with the given name is not found.
     */
    @Override
    public ResponseEntity<?> getLaboratoryByName(String name) {
        List<Laboratory> laboratories = labRepository.findByNameContainingIgnoreCase(name);

        if (!laboratories.isEmpty()) {
            return new ResponseEntity<>(laboratories, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Laboratory not found with name: " + name, HttpStatus.NOT_FOUND);
        }
    }

}
