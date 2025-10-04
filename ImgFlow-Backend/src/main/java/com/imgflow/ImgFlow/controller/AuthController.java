package com.imgflow.ImgFlow.controller;


import com.imgflow.ImgFlow.dto.AuthRequest;
import com.imgflow.ImgFlow.dto.AuthResponse;
import com.imgflow.ImgFlow.dto.SignupRequest;
import com.imgflow.ImgFlow.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public AuthResponse signup(@Valid @RequestBody SignupRequest request) {
        return authService.signup(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) throws Exception {
        return authService.login(request);
    }

}

