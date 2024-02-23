package com.m2i.unilabmanagerbackend.service;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;

public interface JWTservice {

    public String extractUserName(String token);

    public String generateToken(UserDetails userDetails);
    boolean isTokenValid(String token, UserDetails userDetails);

    public String generateRefreshToken(Map<String, Object> extraClaims, UserDetails userDetails);
}
