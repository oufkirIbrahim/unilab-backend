package com.m2i.unilabmanagerbackend.service;

import com.m2i.unilabmanagerbackend.DTO.JwtAuthenticationResponse;
import com.m2i.unilabmanagerbackend.DTO.RefreshTokenRequest;
import com.m2i.unilabmanagerbackend.DTO.SigninRequest;

public interface AuthenticationService {

    public JwtAuthenticationResponse signIn(SigninRequest signinRequest);

    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
