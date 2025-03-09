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
public class CandidateDocumentArticle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long candidateDocumentArticleId;

    @ManyToOne
    @JoinColumn(name = "article_id",nullable = false)
    private CandidateArticle article;

    @ManyToOne
    @JoinColumn(name = "candidate_document_id",nullable = false)
    private CandidateDocument candidateDocument;
}
