package com.yazlab.academichub.entities;

import java.time.LocalDate;

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

    private int numberOfPage;

    private LocalDate date; 

    private String photoLink;

}
