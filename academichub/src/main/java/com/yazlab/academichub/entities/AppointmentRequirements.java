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
public class AppointmentRequirements {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appointmentRequirementsId;

    @Enumerated(EnumType.STRING)
    private POSITION position;

    @ManyToOne
    @JoinColumn(name = "facultygroup_id", nullable = false)
    private FacultyGroup facultyGroup;

    @ManyToOne
    @JoinColumn(name = "prerequisites_id", nullable = false)
    private Prerequisites prerequisites;

    @ManyToOne
    @JoinColumn(name = "publicationcriteria_id", nullable = false)
    private PublicationCriteria publicationCriteria;

    @ManyToOne
    @JoinColumn(name = "minmaxpointcriteria_id", nullable = false)
    private MinMaxPointCriteria minMaxPointCriteria; 
}
