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
public class Table3Action {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long table3ActionId;

    private String actionName; 

    @ManyToOne
    @JoinColumn(name = "facultygroup_id", nullable = false)
    private FacultyGroup facultyGroup;

    private POSITION position;
}
