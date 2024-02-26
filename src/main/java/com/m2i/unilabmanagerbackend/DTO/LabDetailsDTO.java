package com.m2i.unilabmanagerbackend.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class LabDetailsDTO {
    private String role;
    private Integer userId;
    private String firstname;
    private String lastname;
    private String cin;
    private String grade;
    private String som;
    private String email;
    private String phone;
}
