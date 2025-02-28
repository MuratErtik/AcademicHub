package com.yazlab.academichub.entities;

import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
public class FacultyGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long facultyGroupId;

    private String facultyGroupName;


}
