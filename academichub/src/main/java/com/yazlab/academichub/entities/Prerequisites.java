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
public class Prerequisites {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long prerequisitesId;

    @ManyToOne
    @JoinColumn(name = "position", nullable = false)
    private Position position;

    private int foreignLanguageExamPoint;

    private String ethicsCommitteeStatement;
}
