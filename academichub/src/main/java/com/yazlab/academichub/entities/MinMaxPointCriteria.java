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
public class MinMaxPointCriteria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long minMaxPointCriteriaId;

    @ManyToOne
    @JoinColumn(name = "table3Action_id", nullable = false)
    private Table3Action table3Action;

    @ManyToOne
    @JoinColumn(name = "job_offer_id")
    private JobOffer jobOffer;

    private int minPoint; 

    private int maxPoint;
}
