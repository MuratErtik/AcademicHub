package com.yazlab.academichub.entities;

import java.time.LocalDate;

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
public class JuryEvaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long juryEvaluationId;


    @OneToOne
    @JoinColumn(name = "jury_application_id", nullable = false, unique = true) 
    private JuryApplication juryApplication;


    //private String juryevalutationResponse;

    private boolean isApproved;

    private LocalDate evalutationDate;
}
