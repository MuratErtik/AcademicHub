package com.yazlab.academichub.response;

import java.util.Set;

import com.yazlab.academichub.entities.Application;

import lombok.Data;

@Data
public class CandidateResponse {

    private Long userId;

    private String userRole;

    private String name;

    private String lastname;

    private String email;

    private Long mobileNo;

    private Set<Application> applications;    
}


