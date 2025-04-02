package com.yazlab.academichub.entities;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.yazlab.academichub.entities.candidateDocuments.CandidateArticle;
import com.yazlab.academichub.entities.candidateDocuments.CandidateBook;
import com.yazlab.academichub.entities.candidateDocuments.CandidateCitation;
import com.yazlab.academichub.entities.candidateDocuments.CandidateEducationAction;
import com.yazlab.academichub.entities.candidateDocuments.CandidateSMA;

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

    @OneToMany(mappedBy = "application" ,cascade = CascadeType.ALL, orphanRemoval = true )
    private Set<CandidateSMA> smas = new HashSet<>();

    public void addSma(CandidateSMA sma) {
        this.smas.add(sma);
        sma.setApplication(this);
    }

    @OneToMany(mappedBy = "application" ,cascade = CascadeType.ALL, orphanRemoval = true )
    private Set<CandidateBook> books = new HashSet<>();

    public void addBook(CandidateBook book) {
        this.books.add(book);
        book.setApplication(this);
    }

    @OneToMany(mappedBy = "application" ,cascade = CascadeType.ALL, orphanRemoval = true )
    private Set<CandidateCitation> citations = new HashSet<>();

    public void addCitation(CandidateCitation citation) {
        this.citations.add(citation);
        citation.setApplication(this);
    }

    @OneToMany(mappedBy = "application" ,cascade = CascadeType.ALL, orphanRemoval = true )
    private Set<CandidateEducationAction> educationActions  = new HashSet<>();

    public void addEducationAction(CandidateEducationAction educationAction) {
        this.educationActions.add(educationAction);
        educationAction.setApplication(this);
    }

    
}
