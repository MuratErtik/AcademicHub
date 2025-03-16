package com.yazlab.academichub.entities;

import com.yazlab.academichub.domain.POSITION;

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

    @Enumerated(EnumType.STRING)
    private POSITION position;
    
    private String description1;

 

}