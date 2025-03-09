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
public class CandidateBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long candidateBookId;

    private String bookName ;

    private String publisher;

    private String bookCategory ; 

    private int editionNumber;

    private String publicationVenue;

    private String year;

    private String photoLink;




}
