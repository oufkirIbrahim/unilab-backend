package com.m2i.unilabmanagerbackend.DTO;

import com.m2i.unilabmanagerbackend.entity.ApprovalStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LabMaterialOrderDTO {

    private Integer materialId;

    private Integer labID;

    private Date requestDate;

    private ApprovalStatus approvalStatus;

}

