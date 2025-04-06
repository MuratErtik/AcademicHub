package com.yazlab.academichub.response;

import lombok.Data;

@Data
public class PublicationCriteriaResponse {
    private Long id;
    private int articleCount;
    private String table3ActionName;
    private int minPoint;
    private int maxPoint;
    private String table3ActionId;
}