package com.m2i.unilabmanagerbackend.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LabMaterialsDTO {
    private Integer materialId;
    private String materialType;
    private String invNum;
}
