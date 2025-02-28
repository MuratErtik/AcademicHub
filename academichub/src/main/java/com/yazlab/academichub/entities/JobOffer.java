package com.yazlab.academichub.entities;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.yazlab.academichub.domain.POSITION;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class JobOffer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jobOfferId;

    // @ManyToMany(mappedBy = "adminJobOffers")
    // private Set<User> admins;

    // @ManyToMany(mappedBy = "departmentManagerJobOffers")
    // private Set<User> departmentManagers;

    private LocalDate startDate;

    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "department_id",nullable = false)
    private Department department;

    private POSITION position;

    // private ApplicationRequirements applicationRequirements;

    @OneToMany(mappedBy = "jobOffer",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<Application> applications = new HashSet<>();



}
