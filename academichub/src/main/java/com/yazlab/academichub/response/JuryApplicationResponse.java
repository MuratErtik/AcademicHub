package com.yazlab.academichub.response;

import lombok.Data;

@Data
public class JuryApplicationResponse {

    private Long id;

    private UserResponse jury;

    private Long applicationId;

    private String juryevalutationResponse;

    private boolean isApproved;
}
