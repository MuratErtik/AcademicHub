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
public class ImportantThingsToFillTable {
    //Article 7.2

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long importantThingsToFillTableId;

    @ManyToOne
    @JoinColumn(name = "position_id", nullable = false)
    private Position position;
    
    private String description1;

 

}