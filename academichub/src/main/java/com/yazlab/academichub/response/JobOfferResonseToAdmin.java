package com.yazlab.academichub.response;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.yazlab.academichub.entities.Application;
import com.yazlab.academichub.entities.MinMaxPointCriteria;
import com.yazlab.academichub.entities.PublicationCriteria;

import lombok.Data;

@Data
public class JobOfferResonseToAdmin {

    private String title;

    private String description;

    private LocalDateTime startDate; // yyyy-mm-dd-tttt //admin

    private LocalDateTime endDate; // yyyy-mm-dd-tttt //admin

    private String departmentName; // admin

    private String positionName; // admin

    private Set<MinMaxPointCriteria> minMaxPointCriterias = new HashSet<>();

    private Set<PublicationCriteria> publicationCriterias = new HashSet<>();

    private Set<Application> applications = new HashSet<>();
}
