package com.m2i.unilabmanagerbackend.service.impl;

import com.m2i.unilabmanagerbackend.entity.Laboratory;
import com.m2i.unilabmanagerbackend.entity.Material;
import com.m2i.unilabmanagerbackend.entity.Supplier;
import com.m2i.unilabmanagerbackend.entity.User;
import com.m2i.unilabmanagerbackend.repository.LabRepository;
import com.m2i.unilabmanagerbackend.repository.MaterialRepository;
import com.m2i.unilabmanagerbackend.repository.SupplierRepository;
import com.m2i.unilabmanagerbackend.repository.UserRepository;
import com.m2i.unilabmanagerbackend.service.MaterialService;
import com.m2i.unilabmanagerbackend.utils.Util;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MaterialServiceImpl implements MaterialService {

    @Autowired
    private MaterialRepository materialRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private LabRepository labRepository;


    /**
     * Retrieves all materials from the database.
     *
     * @return ResponseEntity with a list of materials and HTTP status OK,
     *         or HTTP status NOT_FOUND if no materials are found.
     */
    @Override
    public ResponseEntity<List<Material>> getAllMaterials() {
        List<Material> materials = materialRepository.findAll();
        if (!materials.isEmpty()) {
            return new ResponseEntity<>(materials, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    /**
     * Saves a new material to the database.
     *
     * @param newMaterial The material to be saved.
     * @return ResponseEntity with the saved material and HTTP status CREATED.
     */
    @Override
    public ResponseEntity<Material> saveMaterial(Material newMaterial) {
        Integer respId = getRespId(newMaterial);
        Integer supplierId = getSupplId(newMaterial);
        Integer labId = getLabId(newMaterial);
        // Set responsible person if ID is provided
        if (respId != null) {
            newMaterial.setResponsiblePerson(userRepository.findById(respId).orElse(null));
        }

        // Set supplier if ID is provided
        if (supplierId != null) {
            newMaterial.setSupplier(supplierRepository.findById(supplierId).orElse(null));
        }

        if(labId != null) {
            newMaterial.setLaboratory(labRepository.getReferenceById(labId));
        }

        Material savedMaterial = materialRepository.save(newMaterial);
        return new ResponseEntity<>(savedMaterial, HttpStatus.CREATED);
    }

    /**
     * Updates an existing material in the database.
     *
     * @param id              The ID of the material to be updated.
     * @param updatedMaterial The updated material data.
     * @return ResponseEntity with HTTP status OK if the update is successful,
     *         or HTTP status NOT_FOUND if the material with the given ID is not found.
     */
    @Override
    public ResponseEntity<?> updateMaterial(Integer id, Material updatedMaterial) {
        Optional<Material> existingMaterialOptional = materialRepository.findById(id);

        if (existingMaterialOptional.isPresent()) {
            Material existingMaterial = existingMaterialOptional.get();

            // Copy non-null properties from updatedMaterial to existingMaterial
            String[] nullProperties = Util.getNullPropertyNames(updatedMaterial);
            BeanUtils.copyProperties(updatedMaterial, existingMaterial, nullProperties);

            // Check and set relationships

            if (updatedMaterial.getResponsiblePerson() != null && getRespId(updatedMaterial) != null)
                existingMaterial.setResponsiblePerson(userRepository.findById(getRespId(updatedMaterial)).orElse(null));

            if (updatedMaterial.getSupplier() != null && getSupplId(updatedMaterial) != null)
                existingMaterial.setSupplier(supplierRepository.findById(getSupplId(updatedMaterial)).orElse(null));

            if(updatedMaterial.getLaboratory() != null &&getLabId(updatedMaterial) != null)
                existingMaterial.setLaboratory(labRepository.getReferenceById(getLabId(updatedMaterial)));
            // Save the updated material
            Material savedMaterial = materialRepository.save(existingMaterial);
            return new ResponseEntity<>(savedMaterial, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Material not found with id: " + id, HttpStatus.NOT_FOUND);
        }
    }


    /**
     * Deletes a material by its ID from the database.
     *
     * @param id The ID of the material to be deleted.
     * @return ResponseEntity with a success message and HTTP status OK if the deletion is successful,
     *         or HTTP status NOT_FOUND if the material with the given ID is not found.
     */
    @Override
    public ResponseEntity<String> deleteMaterialById(Integer id) {
        Optional<Material> materialOptional = materialRepository.findById(id);

        if (materialOptional.isPresent()) {
            materialRepository.deleteById(id);
            return new ResponseEntity<>("Material deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Material not found with id: " + id, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Retrieves a material by its ID from the database.
     *
     * @param id The ID of the material to be retrieved.
     * @return ResponseEntity with the retrieved material and HTTP status OK,
     *         or HTTP status NOT_FOUND if the material with the given ID is not found.
     */
    @Override
    public ResponseEntity<?> getMaterialById(Integer id) {
        Optional<Material> materialOptional = materialRepository.findById(id);

        if (materialOptional.isPresent()) {
            return new ResponseEntity<>(materialOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Material not found with id: " + id, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Retrieves a material by its inventory number from the database.
     *
     * @param inv The inventory number of the material to be retrieved.
     * @return ResponseEntity with a list of materials and HTTP status OK,
     *         or HTTP status NOT_FOUND if the material with the given inventory number is not found.
     */
    @Override
    public ResponseEntity<?> getMaterialByInvNum(String inv) {
        List<Material> materials = materialRepository.findByInventoryNumberContainingIgnoreCase(inv);

        if (!materials.isEmpty()) {
            return new ResponseEntity<>(materials, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Material not found with inventory number: " + inv, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Retrieves materials from the database based on their type.
     *
     * @param type The type of materials to be retrieved.
     * @return ResponseEntity with a list of materials and HTTP status OK if materials are found,
     *         or HTTP status NOT_FOUND if no materials are found with the specified type.
     */
    @Override
    public ResponseEntity<?> getMaterialsByType(String type) {
        // Call the repository method to find materials by type
        List<Material> materials = materialRepository.findByTypeContainingIgnoreCase(type);

        if (!materials.isEmpty()) {
            // Return materials and HTTP status OK if materials are found
            return new ResponseEntity<>(materials, HttpStatus.OK);
        } else {
            // Return a message and HTTP status NOT_FOUND if no materials are found with the specified type
            return new ResponseEntity<>("No materials found with type: " + type, HttpStatus.NOT_FOUND);
        }
    }


    private static Integer getRespId(Material material) {
        return material.getResponsiblePerson().getUserId();
    }
    private static Integer getSupplId(Material material) {
        return material.getSupplier().getSupplierId();
    }

    private static Integer getLabId(Material material){
        return material.getLaboratory().getLabId();
    }
}
