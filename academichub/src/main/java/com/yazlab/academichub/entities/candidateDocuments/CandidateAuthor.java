package com.yazlab.academichub.entities.candidateDocuments;
import com.yazlab.academichub.entities.AuthorType;

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
public class CandidateAuthor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long candidateArticleId;

    private String name; 

    private String surname;

    @ManyToOne
    @JoinColumn(name = "author_type_id",nullable = false)
    private AuthorType authorType;
}
