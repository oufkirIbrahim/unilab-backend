package com.m2i.unilabmanagerbackend.service;

import com.m2i.unilabmanagerbackend.DTO.MaterialOrderDTO;
import com.m2i.unilabmanagerbackend.entity.ApprovalStatus;
import com.m2i.unilabmanagerbackend.entity.MaterialOrder;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MaterialOrderService {
    ResponseEntity<MaterialOrder> orderMaterial(MaterialOrderDTO materialOrderDTO);


    ResponseEntity<List<MaterialOrder>> getMaterialsOrders();

    ResponseEntity<MaterialOrder> setMaterialOrderStatus(Integer id, ApprovalStatus status);

    ResponseEntity<List<MaterialOrder>> getMaterialsOrdersByUserId(Integer id);
}
