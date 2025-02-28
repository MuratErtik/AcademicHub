package com.yazlab.academichub.entities;

import java.time.LocalDate;

import com.yazlab.academichub.domain.APPLICATIONSTATUS;

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
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long applicationId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User candidate;

    @ManyToOne
    @JoinColumn(name = "job_offer_id", nullable = false)
    private JobOffer jobOffer;

    private LocalDate applicationDate;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean isCompleted;

    @Enumerated(EnumType.STRING)
    private APPLICATIONSTATUS applicationStatus;

    // private CandidateDocuments candidateDocuments;

}
