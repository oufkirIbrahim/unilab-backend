package com.m2i.unilabmanagerbackend.DTO;

import com.m2i.unilabmanagerbackend.entity.ApprovalStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class LabConsumableOrderDTO {

    private Integer consumableId;

    private Integer LabId;

    private Integer quantity;

    private final ApprovalStatus approvalStatus = ApprovalStatus.PENDING;

}
