package com.m2i.unilabmanagerbackend.service.impl;

import com.m2i.unilabmanagerbackend.DTO.MaterialOrderDTO;
import com.m2i.unilabmanagerbackend.entity.*;
import com.m2i.unilabmanagerbackend.repository.MaterialRepository;
import com.m2i.unilabmanagerbackend.repository.MaterialOrderRepository;
import com.m2i.unilabmanagerbackend.repository.UserRepository;
import com.m2i.unilabmanagerbackend.service.MaterialOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MaterialOrderServiceImpl implements MaterialOrderService {

    @Autowired
    private MaterialOrderRepository materialOrderRepository;


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MaterialRepository materialRepository;
    @Override
    public ResponseEntity<MaterialOrder> orderMaterial(MaterialOrderDTO materialOrderDTO) {
        User user = userRepository.findById(materialOrderDTO.getPersonId()).get();
        Material material = materialRepository.findById(materialOrderDTO.getMaterialId()).get();

        if(user.getUserId() != null && material.getMaterialId() != null){
            MaterialOrder materialOrder = new MaterialOrder();
            materialOrder.setMaterial(material);
            materialOrder.setPerson(user);
            materialOrder.setApprovalStatus(ApprovalStatus.PENDING);
            materialOrder.setRequestDate(new Date());
            return new ResponseEntity<>(materialOrderRepository.save(materialOrder), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    @Override
    public ResponseEntity<List<MaterialOrder>> getMaterialsOrders(Integer labId) {
        List<MaterialOrder> orders = materialOrderRepository.findByLabId(labId);
        if(!orders.isEmpty()){
            return new ResponseEntity<>(orders, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @Override
    public ResponseEntity<MaterialOrder> setMaterialOrderStatus(Integer id, ApprovalStatus status) {
        Optional<MaterialOrder> materialOrderOpt = materialOrderRepository.findById(id);

        if(!materialOrderOpt.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        MaterialOrder materialOrder = materialOrderOpt.get();
        materialOrder.setApprovalStatus(status);
        materialOrder = materialOrderRepository.save(materialOrder);

        return new ResponseEntity<>(materialOrder,HttpStatus.OK);

    }

    @Override
    public ResponseEntity<List<MaterialOrder>> getMaterialsOrdersByUserId(Integer id) {
        Optional<List<MaterialOrder>> materialOrders = materialOrderRepository.findMaterialsOrdersById(id);
        if(!materialOrders.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);


        return new ResponseEntity<>(materialOrders.get(),HttpStatus.OK);
    }


}
