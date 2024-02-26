package com.m2i.unilabmanagerbackend.controller;

import com.m2i.unilabmanagerbackend.DTO.ConsumablesOrderDTO;
import com.m2i.unilabmanagerbackend.entity.ApprovalStatus;
import com.m2i.unilabmanagerbackend.entity.ConsumableOrder;
import com.m2i.unilabmanagerbackend.service.ConsumablesOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ConsumablesOrderController {

    @Autowired
    private ConsumablesOrderService orderService;

    @PostMapping("/user/orders/consumables")
    public ResponseEntity<ConsumableOrder> orderConsumables(@RequestBody ConsumablesOrderDTO consumablesOrderDTO) {
        return orderService.orderConsumables(consumablesOrderDTO);
    }

    @GetMapping("/user/orders/consumables/userId/{id}")
    public ResponseEntity<List<ConsumableOrder>> getConsumablesOrdersByUserId(@PathVariable Integer id) {
        return orderService.getConsumableOrdersByUserId(id);
    }

    //-------------------------------------responsible--------------------------------------------
    @GetMapping("/responsible/orders/consumables/labId/{labId}")
    public ResponseEntity<List<ConsumableOrder>> getConsumablesOrdersByLabId(@PathVariable Integer labId) {
        return orderService.getConsumablesOrdersByLabId(labId);
    }

    @PatchMapping("/responsible/orders/consumables/{id}/{status}")
    public ResponseEntity<ConsumableOrder> setConsumablesOrderStatus(@PathVariable Integer id, @PathVariable ApprovalStatus status){
        return orderService.setConsumablesOrderStatus(id,status);
    }

}
