package com.m2i.unilabmanagerbackend.controller;

import com.m2i.unilabmanagerbackend.DTO.MaterialOrderDTO;
import com.m2i.unilabmanagerbackend.entity.ApprovalStatus;
import com.m2i.unilabmanagerbackend.entity.MaterialOrder;
import com.m2i.unilabmanagerbackend.service.MaterialOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MaterialOrderController {

    @Autowired
    private MaterialOrderService materialOrderService;

    //-------------------------------------user---------------------------------

    @PostMapping("/user/orders/materials")
    public ResponseEntity<MaterialOrder> orderMaterial(@RequestBody MaterialOrderDTO materialOrder) {
        return materialOrderService.orderMaterial(materialOrder);
    }

    @GetMapping("/user/orders/materials/userId/{id}")
    public ResponseEntity<List<MaterialOrder>> getMaterialsOrdersByUserId(@PathVariable Integer id) {
        return materialOrderService.getMaterialsOrdersByUserId(id);
    }
    //-------------------------------------Admin--------------------------------------------
    @GetMapping("/admin/orders/materials")
    public ResponseEntity<List<MaterialOrder>> getMaterialsOrders() {
        return materialOrderService.getMaterialsOrders();
    }

    @PutMapping("/admin/orders/materials/{id}/{status}")
    public ResponseEntity<MaterialOrder> setMaterialOrderStatus(@PathVariable Integer id, @PathVariable ApprovalStatus status){
        return materialOrderService.setMaterialOrderStatus(id,status);
    }

}
