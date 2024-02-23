package com.m2i.unilabmanagerbackend.controller;

import com.m2i.unilabmanagerbackend.DTO.JwtAuthenticationResponse;
import com.m2i.unilabmanagerbackend.DTO.RefreshTokenRequest;
import com.m2i.unilabmanagerbackend.DTO.SigninRequest;
import com.m2i.unilabmanagerbackend.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SigninRequest signinRequest ){
        return ResponseEntity.ok(authenticationService.signIn(signinRequest));
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthenticationResponse> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest ){
        return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest));
    }


}
