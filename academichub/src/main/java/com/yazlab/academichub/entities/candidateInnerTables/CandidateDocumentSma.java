package com.yazlab.academichub.entities.candidateInnerTables;
import com.yazlab.academichub.entities.CandidateDocument;
import com.yazlab.academichub.entities.candidateDocuments.CandidateSMA;

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
public class CandidateDocumentSma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long candidateDocumentSmaId;

    @ManyToOne
    @JoinColumn(name = "sma_id",nullable = false)
    private CandidateSMA sma;

    @ManyToOne
    @JoinColumn(name = "candidate_document_id",nullable = false)
    private CandidateDocument candidateDocument;
}