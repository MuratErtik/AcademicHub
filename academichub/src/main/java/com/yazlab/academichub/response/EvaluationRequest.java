package com.yazlab.academichub.response;

import lombok.Data;

@Data
public class EvaluationRequest {

    private String response;

    private Boolean isApproved;    
}
