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
public class PositionPosting {

    //Article 10
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Kimlik alanı (primary key)

    private String description1;

    private String description2;

    private String description3;

    private String description4;

    private String description5;

}