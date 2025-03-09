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
public class CandidateAuthor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long candidateArticleId;

    private String name; 

    private String surname;

    private AuthorType authorType;
}
