package com.yazlab.academichub.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class JuryApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) 
    private User jury;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "application_id",nullable = false)
    private Application application;

    private String juryevalutationResponse;

    private boolean isApproved;

    
}