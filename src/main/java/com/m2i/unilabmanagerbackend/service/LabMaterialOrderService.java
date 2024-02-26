package com.m2i.unilabmanagerbackend.service;

import com.m2i.unilabmanagerbackend.DTO.LabMaterialOrderDTO;
import com.m2i.unilabmanagerbackend.entity.ApprovalStatus;
import com.m2i.unilabmanagerbackend.entity.LabMaterialOrder;
import com.m2i.unilabmanagerbackend.entity.MaterialOrder;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface LabMaterialOrderService {
    ResponseEntity<LabMaterialOrder> orderMaterial(LabMaterialOrderDTO labMaterialOrder);

    ResponseEntity<List<LabMaterialOrder>> getMaterialsOrders();

    ResponseEntity<LabMaterialOrder> setMaterialOrderStatus(Integer id, ApprovalStatus status);

    ResponseEntity<List<LabMaterialOrder>> getMaterialsOrdersByLaBId(Integer labId);
}
