package com.yazlab.academichub.request;


import com.yazlab.academichub.domain.USER_ROLE;

import lombok.Data;

@Data
public class OtherSignupRequest {

    private String email;

    private String tcNo;

    private String name;

    private String lastname;

    private Long mobileNo;

    private String password;

    private String departmentName; 

    private USER_ROLE role;

}
