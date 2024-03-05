package com.m2i.unilabmanagerbackend.DTO.statistics;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Consumables {
    private List<Trending> trending;

    private List<ConsumablesData> data;
}
