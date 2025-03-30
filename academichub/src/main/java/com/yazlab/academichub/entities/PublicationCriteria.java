package com.yazlab.academichub.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
public class PublicationCriteria {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long publicationCriteriaId;

    @ManyToOne
    @JoinColumn(name = "table3Action_id", nullable = false)
    private Table3Action table3Action;

    // @ManyToOne
    // @JoinColumn(name = "job_offer_id")
    // private JobOffer jobOffer;

    private int articleCount; 

    // private int leadAuthorCount;
}
