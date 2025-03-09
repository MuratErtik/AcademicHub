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
public class CandidateEducationAction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long candidateEducationActionId;

    private String educationActionCategory;

    private String lectureName;

    private boolean lectureLanguage;

    private String programName;

    private String term;

    private String year;

    private String photoLink;


}
