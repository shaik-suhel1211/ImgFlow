package com.imgflow.ImgFlow.dto;

import lombok.Data;

@Data
public class AuthRequest {
    private String email;
    private String password;
}
//dto's for ensuring sensitive data is not exposed