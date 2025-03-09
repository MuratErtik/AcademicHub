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
public class ArticleAuthor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long articleAuthorId;

    @ManyToOne
    @JoinColumn(name = "article_id",nullable = false)
    private CandidateArticle article;

    @ManyToOne
    @JoinColumn(name = "candidate_author_id",nullable = false)
    private CandidateAuthor candidateAuthor;
}
