package com.yazlab.academichub.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class JobOffer {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jobOfferId;

    private String title;

    private String description;

    // @ManyToMany(mappedBy = "adminJobOffers")
    // private Set<User> admins;

    // @ManyToMany(mappedBy = "departmentManagerJobOffers")
    // private Set<User> departmentManagers;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @ManyToOne
    @JoinColumn(name = "position_id", nullable = false)
    private Position position;

    // @ManyToOne
    // @JoinColumn(name = "appointment_requirment_id",nullable = false)
    // private AppointmentRequirements appointmentRequirements;

    @OneToMany
    @JoinTable(name = "job_offer_criteria_mapping", joinColumns = @JoinColumn(name = "job_offer_id"), inverseJoinColumns = @JoinColumn(name = "criteria_id"))
    private Set<MinMaxPointCriteria> minMaxPointCriterias = new HashSet<>();

    @OneToMany
    @JoinTable(name = "job_offer_publication_criteria_mapping", joinColumns = @JoinColumn(name = "job_offer_id"), inverseJoinColumns = @JoinColumn(name = "criteria_id"))
    private Set<PublicationCriteria> publicationCriterias = new HashSet<>();

    // prerequ do not forget

    @ToString.Exclude
    @OneToMany(mappedBy = "jobOffer", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Application> applications = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "jobOffer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AdminJobOffer> adminJobOffers = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "jobOffer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DepartmentMenagerJobOffer> departmentManagerJobOffers = new ArrayList<>();

    public void addApplication(Application application) {
        this.applications.add(application);
        application.setJobOffer(this);
    }

}
