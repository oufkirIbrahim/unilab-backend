package com.m2i.unilabmanagerbackend.controller;

import com.m2i.unilabmanagerbackend.DTO.LabMaterialOrderDTO;
import com.m2i.unilabmanagerbackend.entity.ApprovalStatus;
import com.m2i.unilabmanagerbackend.entity.LabMaterialOrder;

import com.m2i.unilabmanagerbackend.service.LabMaterialOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LabMaterialOrderController {

    @Autowired
    private LabMaterialOrderService labMaterialOrderService;

    //-------------------------------------responsible---------------------------------

    @PostMapping("/responsible/orders/materials/laboratories")
    public ResponseEntity<LabMaterialOrder> orderMaterial(@RequestBody LabMaterialOrderDTO labMaterialOrderDTO) {
        return labMaterialOrderService.orderMaterial(labMaterialOrderDTO);
    }

    @GetMapping("/responsible/orders/materials/laboratories/{labId}")
    public ResponseEntity<List<LabMaterialOrder>> getMaterialsOrdersByLaBId(@PathVariable Integer labId) {
        return labMaterialOrderService.getMaterialsOrdersByLaBId(labId);
    }
    //-------------------------------------admin--------------------------------------------
    @GetMapping("/admin/orders/materials/laboratories")
    public ResponseEntity<List<LabMaterialOrder>> getMaterialsOrders() {
        return labMaterialOrderService.getMaterialsOrders();
    }

    @PatchMapping("/admin/orders/materials/laboratories/{orderId}/{status}")
    public ResponseEntity<LabMaterialOrder> setMaterialOrderStatus(@PathVariable Integer orderId, @PathVariable ApprovalStatus status){
        return labMaterialOrderService.setMaterialOrderStatus(orderId,status);
    }

}
