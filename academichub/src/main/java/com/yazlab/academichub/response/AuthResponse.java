package com.yazlab.academichub.response;

import com.yazlab.academichub.domain.USER_ROLE;

import lombok.Data;

@Data
public class AuthResponse {

    private String jwt;

    private String message;

    private USER_ROLE role;
}
