package com.m2i.unilabmanagerbackend.service;

import com.m2i.unilabmanagerbackend.DTO.ConsumablesOrderDTO;
import com.m2i.unilabmanagerbackend.DTO.LabConsumableOrderDTO;
import com.m2i.unilabmanagerbackend.entity.ApprovalStatus;
import com.m2i.unilabmanagerbackend.entity.LabConsumableOrder;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface LabConsumablesOrderService {
    ResponseEntity<LabConsumableOrder> orderConsumables(LabConsumableOrderDTO labConsumablesOrderDTO);

    ResponseEntity<List<LabConsumableOrder>> getLabConsumablesOrdersByLabId(Integer id);

    ResponseEntity<List<LabConsumableOrder>> getLabConsumablesOrders();

    ResponseEntity<LabConsumableOrder> setLabConsumablesOrderStatus(Integer id, ApprovalStatus status);
}
