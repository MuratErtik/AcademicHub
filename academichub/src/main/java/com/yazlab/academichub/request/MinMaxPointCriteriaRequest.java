package com.yazlab.academichub.request;

import lombok.Data;

@Data
public class MinMaxPointCriteriaRequest {
    private Long table3ActionId;
    private int minPoint;
    private int maxPoint;
}