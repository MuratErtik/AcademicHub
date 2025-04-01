package com.yazlab.academichub.entities.candidateDocuments;

import java.time.LocalDateTime;

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
public class CandidateSMA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long candidateSMAId;

    private String sMACategory;

    private String smaName; 

    private String conferenceName;

    private String place;

    private LocalDateTime date; 

    private String photoLink;

    @ManyToOne
    @JoinColumn(name = "application_id", nullable = false)
    private Application application;

}
