package com.m2i.unilabmanagerbackend.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MaterialAssignmentListDTO {
    private Integer personId;
    private Integer materialId;
    private String firstname;
    private String lastname;
    private String labName;
    private String materialType;
    private String invNum;

}
