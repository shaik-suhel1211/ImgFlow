package com.imgflow.ImgFlow.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignupRequest {
    @Size(min = 3,max = 30,message = "Username must be at least 3 characters")
    private String username;
    @Size(min = 12,max = 50)
    private String email;
    @Size(min = 8,message = "Password must be greater than 8 characters")
    private String password;
}
