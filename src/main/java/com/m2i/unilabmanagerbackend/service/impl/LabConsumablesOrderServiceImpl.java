package com.m2i.unilabmanagerbackend.service.impl;

import com.m2i.unilabmanagerbackend.DTO.ConsumablesOrderDTO;
import com.m2i.unilabmanagerbackend.DTO.LabConsumableOrderDTO;
import com.m2i.unilabmanagerbackend.entity.*;
import com.m2i.unilabmanagerbackend.repository.*;
import com.m2i.unilabmanagerbackend.service.ConsumablesOrderService;
import com.m2i.unilabmanagerbackend.service.LabConsumablesOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LabConsumablesOrderServiceImpl implements LabConsumablesOrderService {


        @Autowired
        private LabConsumablesOrderRepository labConsumablesOrderRepository;


        @Autowired
        private UserRepository userRepository;

        @Autowired
        private ConsumableRepository consumableRepository;

        @Autowired
        private LabRepository labRepository;

        @Override
        public ResponseEntity<LabConsumableOrder> orderConsumables(LabConsumableOrderDTO labConsumableOrderDTO) {
            Laboratory laboratory = labRepository.findById(labConsumableOrderDTO.getLabId()).get();
            Consumable consumables = consumableRepository.findById(labConsumableOrderDTO.getConsumableId()).get();

            if(laboratory.getLabId() != null && consumables.getConsumableId() != null){
                LabConsumableOrder labConsumablesOrder = new LabConsumableOrder();
                labConsumablesOrder.setConsumable(consumables);
                labConsumablesOrder.setLaboratory(laboratory);
                labConsumablesOrder.setQuantity(labConsumableOrderDTO.getQuantity());
                labConsumablesOrder.setApprovalStatus(labConsumableOrderDTO.getApprovalStatus());
                //consumablesOrder.setRequestDate(consumablesOrderDTO.getRequestDate());

                return new ResponseEntity<>(labConsumablesOrderRepository.save(labConsumablesOrder), HttpStatus.CREATED);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


        @Override
        public ResponseEntity<List<LabConsumableOrder>> getLabConsumablesOrders() {
            List<LabConsumableOrder> orders = labConsumablesOrderRepository.findAll();
            if(!orders.isEmpty()){
                return new ResponseEntity<>(orders, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        @Override
        public ResponseEntity<List<LabConsumableOrder>> getLabConsumablesOrdersByLabId(Integer labId) {
            List<LabConsumableOrder> orders = labConsumablesOrderRepository.findConsumablesOrdersByLabId(labId);
            if(!orders.isEmpty()){
                return new ResponseEntity<>(orders, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        @Override
        public ResponseEntity<LabConsumableOrder> setLabConsumablesOrderStatus(Integer id, ApprovalStatus status) {
            Optional<LabConsumableOrder> labConsumablesOrderOpt = labConsumablesOrderRepository.findById(id);

            if(!labConsumablesOrderOpt.isPresent()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            LabConsumableOrder labConsumablesOrder = labConsumablesOrderOpt.get();
            labConsumablesOrder.setApprovalStatus(status);
            labConsumablesOrder = labConsumablesOrderRepository.save(labConsumablesOrder);

            return new ResponseEntity<>(labConsumablesOrder,HttpStatus.OK);

        }

}
