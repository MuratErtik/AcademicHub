package com.yazlab.academichub.request;

import lombok.Data;

@Data
public class SignupRequest {
    
    private String email;

    private String tcNo;

    private String name;

    private String lastname;

    private Long mobileNo;


}
