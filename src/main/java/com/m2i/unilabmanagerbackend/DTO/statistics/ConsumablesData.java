package com.m2i.unilabmanagerbackend.DTO.statistics;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsumablesData {
    private String type;

    private Integer quantity;
}
