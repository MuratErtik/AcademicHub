package com.yazlab.academichub.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationValidationResponse {
    private String position;
    private String facultyGroup;
    private boolean isValid;
}