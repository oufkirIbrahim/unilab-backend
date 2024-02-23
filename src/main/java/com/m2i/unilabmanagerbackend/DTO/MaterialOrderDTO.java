package com.m2i.unilabmanagerbackend.DTO;


import com.m2i.unilabmanagerbackend.entity.ApprovalStatus;
import lombok.Data;
import org.apache.catalina.core.AprStatus;

import java.util.Date;

@Data
public class MaterialOrderDTO {

    private Integer materialId;

    private Integer personId;

    private Date requestDate;

    private ApprovalStatus approvalStatus;


}
