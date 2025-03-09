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
public class CandidateCitation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long candidateCitationId;

    private String citationCategory;

    private String articleToCitation;

    private int numberOfCitation;

    private String photoLink;

}
