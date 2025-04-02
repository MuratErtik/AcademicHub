package com.yazlab.academichub.entities.candidateDocuments;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yazlab.academichub.entities.Application;

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
public class CandidateContributionActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long candidateContributionActivityId;

    private String category;

    private String departmentName;

    private String year;

    private String photoLink;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "application_id", nullable = false)
    private Application application;
}
