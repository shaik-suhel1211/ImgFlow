package com.imgflow.ImgFlow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {
    private String jwtToken;
}
//dto's for ensuring sensitive data is not exposed