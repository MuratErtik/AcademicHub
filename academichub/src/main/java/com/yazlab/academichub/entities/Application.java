package com.yazlab.academichub.entities;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.yazlab.academichub.entities.candidateDocuments.CandidateArticle;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

    private LocalDateTime applicationDate;

    // @Enumerated(EnumType.STRING)
    // private IsCompleted isCompleted = IsCompleted.START;

    @ManyToOne
    @JoinColumn(name = "application_status_id")
    private ApplicationStatus applicationStatus;

    // @ManyToOne
    // @JoinColumn(name = "candidate_document_id", nullable = false)
    // private CandidateDocument candidateDocument;

    @OneToMany(mappedBy = "application" ,cascade = CascadeType.ALL, orphanRemoval = true )
    private Set<CandidateArticle> articles = new HashSet<>();

    public void addArticle(CandidateArticle article) {
        this.articles.add(article);
        article.setApplication(this);
    }
}
