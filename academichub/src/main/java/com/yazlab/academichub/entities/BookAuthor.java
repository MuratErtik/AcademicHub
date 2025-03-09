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
public class BookAuthor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookAuthorId;

    @ManyToOne
    @JoinColumn(name = "book_id",nullable = false)
    private CandidateBook book;

    @ManyToOne
    @JoinColumn(name = "candidate_author_id",nullable = false)
    private CandidateAuthor candidateAuthor;
}
