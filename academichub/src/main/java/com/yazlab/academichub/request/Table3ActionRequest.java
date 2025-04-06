package com.yazlab.academichub.request;

import lombok.Data;

@Data
public class Table3ActionRequest {
    private String actionName;
    private Long facultyGroupId;
    private Long positionId;
}
