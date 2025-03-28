package com.yazlab.academichub.response;

import com.yazlab.academichub.entities.UserRole;

import lombok.Data;

@Data
public class AuthResponse {

    private String jwt;

    private String message;

    private UserRole role;
}
