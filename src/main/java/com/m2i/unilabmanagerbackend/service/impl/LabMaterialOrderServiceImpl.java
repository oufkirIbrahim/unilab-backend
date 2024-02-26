package com.m2i.unilabmanagerbackend.service.impl;

import com.m2i.unilabmanagerbackend.DTO.LabMaterialOrderDTO;
import com.m2i.unilabmanagerbackend.DTO.MaterialOrderDTO;
import com.m2i.unilabmanagerbackend.entity.*;
import com.m2i.unilabmanagerbackend.repository.*;
import com.m2i.unilabmanagerbackend.service.LabMaterialOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LabMaterialOrderServiceImpl implements LabMaterialOrderService {

    @Autowired
    private LabMaterialOrderRepository labMaterialOrderRepository;

    @Autowired
    private LabRepository labRepository;

    @Autowired
    private MaterialRepository materialRepository;
    @Override
    public ResponseEntity<LabMaterialOrder> orderMaterial(LabMaterialOrderDTO labMaterialOrderDTO) {
        Laboratory laboratory = labRepository.findById(labMaterialOrderDTO.getLabID()).get();
        Material material = materialRepository.findById(labMaterialOrderDTO.getMaterialId()).get();

        if(laboratory.getLabId() != null && material.getMaterialId() != null){
            LabMaterialOrder materialOrder = new LabMaterialOrder();
            materialOrder.setMaterial(material);
            materialOrder.setLaboratory(laboratory);
            materialOrder.setApprovalStatus(labMaterialOrderDTO.getApprovalStatus());
            materialOrder.setRequestDate(labMaterialOrderDTO.getRequestDate());
            return new ResponseEntity<>(labMaterialOrderRepository.save(materialOrder), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    @Override
    public ResponseEntity<List<LabMaterialOrder>> getMaterialsOrders() {
        List<LabMaterialOrder> orders = labMaterialOrderRepository.findAll();
        if(!orders.isEmpty()){
            return new ResponseEntity<>(orders, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @Override
    public ResponseEntity<LabMaterialOrder> setMaterialOrderStatus(Integer id, ApprovalStatus status) {
        Optional<LabMaterialOrder> materialOrderOpt = labMaterialOrderRepository.findById(id);

        if(!materialOrderOpt.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        LabMaterialOrder labMaterialOrder = materialOrderOpt.get();
        labMaterialOrder.setApprovalStatus(status);
        labMaterialOrder = labMaterialOrderRepository.save(labMaterialOrder);

        return new ResponseEntity<>(labMaterialOrder,HttpStatus.OK);

    }

    @Override
    public ResponseEntity<List<LabMaterialOrder>> getMaterialsOrdersByLaBId(Integer id) {
        Optional<List<LabMaterialOrder>> materialOrders = labMaterialOrderRepository.findMaterialsOrdersByLabId(id);
        if(!materialOrders.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);


        return new ResponseEntity<>(materialOrders.get(),HttpStatus.OK);
    }
}
