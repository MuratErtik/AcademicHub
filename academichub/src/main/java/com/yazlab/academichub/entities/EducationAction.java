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
public class EducationAction {

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)    
    private Category category;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long educationActionId;

    private String content;

    private int point;


}
