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
public class MinMaxPointCriteria {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long minMaxPointCriteriaId;

    
    @ManyToOne
    @JoinColumn(name = "table3Action_id", nullable = false)
    private Table3Action table3Action;



    private int minPoint; 

    private int maxPoint;
}
