package com.yazlab.academichub.response;



import lombok.Data;

@Data
public class UserResponse {

    private Long userId;

    private String userRole;

    private String name;

    private String lastname;

    private String email;

    private Long mobileNo;

    private String departmentName;
}
