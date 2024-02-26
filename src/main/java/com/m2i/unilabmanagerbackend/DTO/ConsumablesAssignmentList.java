package com.m2i.unilabmanagerbackend.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ConsumablesAssignmentList {
    private Integer labId;
    private String labName;
    private Integer consumableId;
    private String consumableType;
    private Integer quantity;

}
