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
public class Table3Action {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long table3ActionId;

    private String actionName; 

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "facultygroup_id", nullable = false)
    private FacultyGroup facultyGroup;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "position", nullable = false)
    private Position position;
}
