package com.yazlab.academichub.entities.candidateInnerTables;
import com.yazlab.academichub.entities.CandidateDocument;
import com.yazlab.academichub.entities.candidateDocuments.CandidateContributionActivity;

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
public class CandidateDocumentContributionActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long candidateDocumentContributionActivityId;

    @ManyToOne
    @JoinColumn(name = "candidate_contribution_activity_id",nullable = false)
    private CandidateContributionActivity candidateContributionActivity;

    @ManyToOne
    @JoinColumn(name = "candidate_document_id",nullable = false)
    private CandidateDocument candidateDocument;
}
