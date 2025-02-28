package com.yazlab.academichub.entities;



import com.fasterxml.jackson.annotation.JsonProperty;
import com.yazlab.academichub.domain.USER_ROLE;

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
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "tc_idn", unique = true, nullable = false)    private Long tcIdN;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private USER_ROLE userRole;

    private String name;

    private String lastname;

    private String email;

    @ManyToOne
    @JoinColumn(name = "department_id",nullable = false)
    private Department department;

    // @ManyToMany
    // @JoinTable(
    //     name = "departmentmanager_joboffer",
    //     joinColumns = @JoinColumn(name = "user_id"),
    //     inverseJoinColumns = @JoinColumn(name = "job_offer_id") 
    // )
    // private Set<JobOffer> departmentManagerJobOffers = new HashSet<>();

    // @ManyToMany
    // @JoinTable(
    //     name = "admin_joboffer",
    //     joinColumns = @JoinColumn(name = "user_id"),
    //     inverseJoinColumns = @JoinColumn(name = "job_offer_id") 
    // )
    // private Set<JobOffer> adminJobOffers = new HashSet<>();

    // @ManyToMany(mappedBy = "applicationJuries")
    // private Set<Application> juryApplications;

    


}
