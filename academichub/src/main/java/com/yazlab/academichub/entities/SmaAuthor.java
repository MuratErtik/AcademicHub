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
public class SmaAuthor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long articleAuthorId;

    @ManyToOne
    @JoinColumn(name = "sma_id",nullable = false)
    private CandidateSMA sma;

    @ManyToOne
    @JoinColumn(name = "candidate_author_id",nullable = false)
    private CandidateAuthor candidateAuthor;
}
