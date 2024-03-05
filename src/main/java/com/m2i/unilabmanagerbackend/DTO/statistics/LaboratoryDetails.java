package com.m2i.unilabmanagerbackend.DTO.statistics;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LaboratoryDetails {
    private Integer id;

    private String image;

    private String name;

    private Integer admins;

    private Integer users;
}
