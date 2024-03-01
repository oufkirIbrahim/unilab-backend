package com.m2i.unilabmanagerbackend.controller;


import com.m2i.unilabmanagerbackend.DTO.LabConsumableOrderDTO;
import com.m2i.unilabmanagerbackend.entity.ApprovalStatus;
import com.m2i.unilabmanagerbackend.entity.LabConsumableOrder;
import com.m2i.unilabmanagerbackend.service.LabConsumablesOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LabConsumablesOrderController {

    @Autowired
    private LabConsumablesOrderService labConsumablesOrderService;

    //============================================responsible=================================================

    @PostMapping("/responsible/orders/consumables/laboratories")
    public ResponseEntity<LabConsumableOrder> orderConsumables(@RequestBody LabConsumableOrderDTO labConsumablesOrderDTO) {
        return labConsumablesOrderService.orderConsumables(labConsumablesOrderDTO);
    }

    @GetMapping("/responsible/orders/consumables/laboratories/labId/{id}")
    public ResponseEntity<List<LabConsumableOrder>> getConsumablesOrdersByLabId(@PathVariable Integer id) {
        return labConsumablesOrderService.getLabConsumablesOrdersByLabId(id);
    }

    //===============================================admin====================================================
    @GetMapping("/admin/orders/consumables/laboratories")
    public ResponseEntity<List<LabConsumableOrder>> getLabConsumablesOrders() {
        return labConsumablesOrderService.getLabConsumablesOrders();
    }

    @PatchMapping("/admin/orders/consumables/laboratories/{id}/{status}")
    public ResponseEntity<LabConsumableOrder> setConsumablesOrderStatus(@PathVariable Integer id, @PathVariable ApprovalStatus status){
        return labConsumablesOrderService.setLabConsumablesOrderStatus(id,status);
    }

}
