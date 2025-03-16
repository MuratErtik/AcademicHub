package com.yazlab.academichub.entities.candidateDocuments;
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
public class CandidateAward {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long candidateAwardId;

    private String category;

    private String uniName;

    private String year;

    private String photoName;
}
