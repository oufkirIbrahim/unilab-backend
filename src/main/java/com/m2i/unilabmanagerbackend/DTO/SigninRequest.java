package com.m2i.unilabmanagerbackend.DTO;

import lombok.Data;

@Data
public class SigninRequest {
    private String email;
    private String password;
}
