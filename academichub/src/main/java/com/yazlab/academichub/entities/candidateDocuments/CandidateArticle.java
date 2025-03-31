package com.yazlab.academichub.entities.candidateDocuments;

import java.util.HashSet;
import java.util.Set;

import com.yazlab.academichub.entities.Application;
import com.yazlab.academichub.entities.ArticleType;

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
public class CandidateArticle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long candidateArticleId;

    private String articleName;

    @ManyToOne
    @JoinColumn(name = "article_type_id", nullable = false)
    private ArticleType articleType;

    private String articleCategory; // A1,A2... it will equal to category+id in Article class.

    private int authorCount;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "article_id") 
    private Set<CandidateAuthor> authors = new HashSet<>();

    private String photoLink;

    @ManyToOne
    @JoinColumn(name = "application_id", nullable = false)
    private Application application;

}
