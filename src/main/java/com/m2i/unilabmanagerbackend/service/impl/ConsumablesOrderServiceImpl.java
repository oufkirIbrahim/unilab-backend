package com.m2i.unilabmanagerbackend.service.impl;

import com.m2i.unilabmanagerbackend.DTO.ConsumablesOrderDTO;
import com.m2i.unilabmanagerbackend.entity.*;
import com.m2i.unilabmanagerbackend.repository.ConsumableRepository;
import com.m2i.unilabmanagerbackend.repository.ConsumablesOrderRepository;
import com.m2i.unilabmanagerbackend.repository.LabRepository;
import com.m2i.unilabmanagerbackend.repository.UserRepository;
import com.m2i.unilabmanagerbackend.service.ConsumablesOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsumablesOrderServiceImpl implements ConsumablesOrderService {


    @Autowired
    private ConsumablesOrderRepository consumablesOrderRepository;


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ConsumableRepository consumableRepository;

    @Autowired
    private LabRepository labRepository;

    @Override
    public ResponseEntity<ConsumableOrder> orderConsumables(ConsumablesOrderDTO consumablesOrderDTO) {
        User user = userRepository.findById(consumablesOrderDTO.getPersonId()).get();
        Consumable consumables = consumableRepository.findById(consumablesOrderDTO.getConsumableId()).get();
        Laboratory laboratory = labRepository.findById(user.getLabId()).get();
        if(user.getUserId() != null && consumables.getConsumableId() != null){
            ConsumableOrder consumablesOrder = new ConsumableOrder();
            consumablesOrder.setConsumable(consumables);
            consumablesOrder.setPerson(user);
            consumablesOrder.setApprovalStatus(consumablesOrderDTO.getApprovalStatus());
            //consumablesOrder.setRequestDate(consumablesOrderDTO.getRequestDate());

            return new ResponseEntity<>(consumablesOrderRepository.save(consumablesOrder), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    @Override
    public ResponseEntity<List<ConsumableOrder>> getConsumablesOrders() {
        List<ConsumableOrder> orders = consumablesOrderRepository.findAll();
        if(!orders.isEmpty()){
            return new ResponseEntity<>(orders, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<List<ConsumableOrder>> getConsumablesOrdersByLabId(Integer labId) {
        List<ConsumableOrder> orders = consumablesOrderRepository.findConsumableOrdersByLabId(labId);
        if(!orders.isEmpty()){
            return new ResponseEntity<>(orders, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<ConsumableOrder> setConsumablesOrderStatus(Integer id, ApprovalStatus status) {
        Optional<ConsumableOrder> consumablesOrderOpt = consumablesOrderRepository.findById(id);

        if(!consumablesOrderOpt.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        ConsumableOrder consumablesOrder = consumablesOrderOpt.get();
        consumablesOrder.setApprovalStatus(status);
        consumablesOrder = consumablesOrderRepository.save(consumablesOrder);

        return new ResponseEntity<>(consumablesOrder,HttpStatus.OK);

    }

    @Override
    public ResponseEntity<List<ConsumableOrder>> getConsumableOrdersByUserId(Integer id) {
        Optional<List<ConsumableOrder>> consumablesOrders = consumablesOrderRepository.findConsumableOrdersByPersonId(id);
        if(!consumablesOrders.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);


        return new ResponseEntity<>(consumablesOrders.get(),HttpStatus.OK);
    }
}
