package com.m2i.unilabmanagerbackend.service;

import com.m2i.unilabmanagerbackend.DTO.ConsumablesOrderDTO;
import com.m2i.unilabmanagerbackend.DTO.MaterialOrderDTO;
import com.m2i.unilabmanagerbackend.entity.ApprovalStatus;
import com.m2i.unilabmanagerbackend.entity.ConsumableOrder;
import com.m2i.unilabmanagerbackend.entity.MaterialOrder;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ConsumablesOrderService {
    ResponseEntity<ConsumableOrder> orderConsumables(ConsumablesOrderDTO consumablesOrderDTO);

    ResponseEntity<List<ConsumableOrder>> getConsumablesOrders();

    ResponseEntity<ConsumableOrder> setConsumablesOrderStatus(Integer id, ApprovalStatus status);

    ResponseEntity<List<ConsumableOrder>> getConsumableOrdersByUserId(Integer id);
}
