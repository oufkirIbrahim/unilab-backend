package com.m2i.unilabmanagerbackend.DTO;

import com.m2i.unilabmanagerbackend.entity.Role;
import com.m2i.unilabmanagerbackend.entity.User;
import lombok.Data;

@Data
public class JwtAuthenticationResponse {
    private String token;
    private String refreshToken;
    private Role role;
    private Integer userId;
}
