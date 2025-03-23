package com.yazlab.academichub.request;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.yazlab.academichub.domain.POSITION;
import com.yazlab.academichub.entities.MinMaxPointCriteria;
import com.yazlab.academichub.entities.PublicationCriteria;


import lombok.Data;

@Data
public class CreateJobOfferRequest {

    private String title;

    private String description;

    private LocalDateTime startDate; //yyyy-mm-dd-tttt //admin

    private LocalDateTime endDate; //yyyy-mm-dd-tttt   //admin

    private String departmentName; //admin

    private POSITION position; //admin

    private Set<MinMaxPointCriteria> minMaxPointCriterias = new HashSet<>();

    private Set<PublicationCriteria> publicationCriterias = new HashSet<>();

    
}
