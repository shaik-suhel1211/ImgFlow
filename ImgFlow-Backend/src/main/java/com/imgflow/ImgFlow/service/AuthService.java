package com.imgflow.ImgFlow.service;

import com.imgflow.ImgFlow.dto.AuthRequest;
import com.imgflow.ImgFlow.dto.AuthResponse;
import com.imgflow.ImgFlow.dto.SignupRequest;
import com.imgflow.ImgFlow.entity.User;
import com.imgflow.ImgFlow.repository.UserRepository;
import com.imgflow.ImgFlow.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

//for every signup and login a new jwt token is generated,to ensure authentication is not failed if old one expires

    public AuthResponse signup(SignupRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setAccessToken(UUID.randomUUID().toString());
            userRepository.save(user);
            String jwt=jwtTokenUtil.generateToken(user.getEmail());
        return new AuthResponse(jwt);
    }

    public AuthResponse login(AuthRequest request) throws Exception {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new Exception("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new Exception("Invalid credentials");
        }

        String jwt = jwtTokenUtil.generateToken(user.getEmail());
        return new AuthResponse(jwt);
    }
}
