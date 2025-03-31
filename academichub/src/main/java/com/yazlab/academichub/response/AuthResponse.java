package com.yazlab.academichub.response;


import lombok.Data;

@Data
public class AuthResponse {

    private String jwt;

    private String message;

    private String role;

    private Long userId;
}
