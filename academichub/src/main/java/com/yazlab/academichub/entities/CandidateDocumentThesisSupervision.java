package com.yazlab.academichub.entities;
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
public class CandidateDocumentThesisSupervision {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long candidateDocumentThesisSupervisionId;

    @ManyToOne
    @JoinColumn(name = "article_id",nullable = false)
    private CandidateThesisSupervision thesisSupervision;

    @ManyToOne
    @JoinColumn(name = "candidate_document_id",nullable = false)
    private CandidateDocument candidateDocument;
}
