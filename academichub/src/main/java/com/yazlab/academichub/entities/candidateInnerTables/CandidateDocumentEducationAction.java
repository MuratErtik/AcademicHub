package com.yazlab.academichub.entities.candidateInnerTables;
import com.yazlab.academichub.entities.CandidateDocument;
import com.yazlab.academichub.entities.candidateDocuments.CandidateEducationAction;

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
public class CandidateDocumentEducationAction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long candidateDocumentEducationActionId;

    @ManyToOne
    @JoinColumn(name = "education_action_id",nullable = false)
    private CandidateEducationAction educationAction;

    @ManyToOne
    @JoinColumn(name = "candidate_document_id",nullable = false)
    private CandidateDocument candidateDocument;
}
