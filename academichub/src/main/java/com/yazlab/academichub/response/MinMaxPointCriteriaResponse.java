package com.yazlab.academichub.response;

import lombok.Data;

@Data
public class MinMaxPointCriteriaResponse {
    private Long id;
    private int minPoint;
    private int maxPoint;
    private String table3ActionName;
}