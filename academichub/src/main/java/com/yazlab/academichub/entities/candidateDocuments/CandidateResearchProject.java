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
public class CandidateResearchProject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long candidateResearchProjectId;

    private String category;

    private String name;

    private Long number;

    private String year;

    private String uniName;

    private String photoLink;

}
