package com.m2i.unilabmanagerbackend.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public UserDetailsService userDetailsService();
}
