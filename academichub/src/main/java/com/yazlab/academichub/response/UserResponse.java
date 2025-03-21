package com.yazlab.academichub.response;

import com.yazlab.academichub.domain.USER_ROLE;
import com.yazlab.academichub.entities.Department;

import lombok.Data;

@Data
public class UserResponse {

    private USER_ROLE userRole;

    private String name;

    private String lastname;

    private String email;

    private Long mobileNo;

    private Department department;
}
