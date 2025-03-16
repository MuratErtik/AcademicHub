package com.yazlab.academichub.entities.candidateDocuments;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
public class CandidateThesisSupervision {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long candidateThesisSupervisionId;

    private String category;

    private String studentName;

    private String thesisName;

    private String enstitu;

    private String year;

    private String photoLink;
}
