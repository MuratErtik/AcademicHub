package com.yazlab.academichub.response;

import com.yazlab.academichub.entities.Department;
import com.yazlab.academichub.entities.UserRole;

import lombok.Data;

@Data
public class UserResponse {

    private UserRole userRole;

    private String name;

    private String lastname;

    private String email;

    private Long mobileNo;

    private Department department;
}
