package com.m2i.unilabmanagerbackend.DTO.statistics;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.DecimalFormat;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Gauge {

    private Double consumables;
    private Double materials;

    private Double budget;
    private Double activeSuppliers;


}
