package com.m2i.unilabmanagerbackend.DTO;

import com.m2i.unilabmanagerbackend.entity.ApprovalStatus;
import lombok.Data;

import java.util.Date;

@Data
public class ConsumableAssignmentDTO {

    private Integer laboratoryId;

    private Integer consumableId;


    private Integer quantity;

    private Date assignmentDate;

    private ApprovalStatus approvalStatus;

}
