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
public class PublicationCriteria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long publicationCriteriaId;

    @ManyToOne
    @JoinColumn(name = "table3Action_id", nullable = false)
    private Table3Action table3Action;

    private int articleCount; 

    private int LeadAuthorCount;
}
