package com.imgflow.ImgFlow.dto;

import lombok.Data;

@Data
public class UserResponse {
    private String username;
    private String email;
    private String accessToken;
}
//dto's for ensuring sensitive data is not exposed like password here..