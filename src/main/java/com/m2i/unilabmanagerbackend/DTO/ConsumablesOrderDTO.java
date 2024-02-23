package com.m2i.unilabmanagerbackend.DTO;

import com.m2i.unilabmanagerbackend.entity.ApprovalStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsumablesOrderDTO {

    private Integer consumableId;

    private Integer personId;

    //private final Date requestDate = new Date();

    private Integer quantity;

    private final ApprovalStatus approvalStatus = ApprovalStatus.PENDING;
}
