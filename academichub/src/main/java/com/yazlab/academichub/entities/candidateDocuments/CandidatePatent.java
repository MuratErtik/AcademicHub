package com.yazlab.academichub.entities.candidateDocuments;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yazlab.academichub.entities.Application;

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
public class CandidatePatent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long candidatePatentId;

    private String category;

    private String patentName;

    private String year;

    private String photoPath;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "application_id", nullable = false)
    private Application application;
}
