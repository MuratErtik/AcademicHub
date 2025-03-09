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
public class CandidateArticle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long candidateArticleId;

    private String articleName ;

    private ArticleType articleType ; 

    private String articleCategory ; //A1,A2...  it will equal to category+id in Article class.

    private int authorCount;

    private String photoLink;




}
