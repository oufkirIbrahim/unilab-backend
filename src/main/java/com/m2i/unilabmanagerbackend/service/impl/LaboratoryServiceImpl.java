package com.m2i.unilabmanagerbackend.service.impl;

import com.m2i.unilabmanagerbackend.DTO.LabDetailsDTO;
import com.m2i.unilabmanagerbackend.DTO.LabMaterialsDTO;
import com.m2i.unilabmanagerbackend.entity.Laboratory;
import com.m2i.unilabmanagerbackend.entity.Material;
import com.m2i.unilabmanagerbackend.entity.User;
import com.m2i.unilabmanagerbackend.repository.LabRepository;
import com.m2i.unilabmanagerbackend.repository.UserRepository;
import com.m2i.unilabmanagerbackend.service.LaboratoryService;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

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

    @Override
    public ResponseEntity<?> getLaboratoryByResponsibleId(Integer id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            Optional<Laboratory> laboratory = labRepository.findByResponsibleId(user.get().getUserId());
            if(laboratory.isPresent()){
                return new ResponseEntity<>(laboratory, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Laboratory not found with Responsible Id: " + id, HttpStatus.NOT_FOUND);
    }

    @Override
    public void exportLabDetails(HttpServletResponse response, Integer labId) throws JRException, IOException {
        Laboratory lab = labRepository.getReferenceById(labId);

        User resp = lab.getResponsiblePerson();
        User deputy = lab.getDeputyPerson();

        LabDetailsDTO respDto = LabDetailsDTO.builder()
                .userId(resp.getUserId())
                .role("Chef")
                .firstname(resp.getFirstName())
                .lastname(resp.getLastName())
                .cin(resp.getCin())
                .grade(resp.getGrade())
                .som(resp.getSomNumber())
                .email(resp.getEmail())
                .phone(resp.getPhone())
                .build();

        LabDetailsDTO respDto1 = LabDetailsDTO.builder()
                .userId(resp.getUserId())
                .role("Chef")
                .firstname(resp.getFirstName())
                .lastname(resp.getLastName())
                .cin(resp.getCin())
                .grade(resp.getGrade())
                .som(resp.getSomNumber())
                .email(resp.getEmail())
                .phone(resp.getPhone())
                .build();
        LabDetailsDTO deputyDto = LabDetailsDTO.builder()
                .userId(deputy.getUserId())
                .role("Adjoint")
                .firstname(deputy.getFirstName())
                .lastname(deputy.getLastName())
                .cin(deputy.getCin())
                .grade(deputy.getGrade())
                .som(deputy.getSomNumber())
                .email(deputy.getEmail())
                .phone(deputy.getPhone())
                .build();

        List<LabDetailsDTO> tableSet  = new ArrayList<>();
        tableSet.add(respDto);
        tableSet.add(respDto1);
        tableSet.add(deputyDto);
        // Load the JRXML template from the classpath
        InputStream templateStream = getClass().getResourceAsStream("/labDetails.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(templateStream);

        // Create a data source
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(tableSet);

        // Set report parameters (if needed)
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("labDataSet", dataSource);
        parameters.put("labName",lab.getName());
        parameters.put("labId",lab.getLaboratoryId());
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
    }

}
