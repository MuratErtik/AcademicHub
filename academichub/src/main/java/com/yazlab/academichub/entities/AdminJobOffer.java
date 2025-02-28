package com.yazlab.academichub.entities;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class AdminJobOffer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) 
    private User admin;

    @ManyToOne
    @JoinColumn(name = "job_offer_id",nullable = false)
    private JobOffer jobOffer;

    private LocalDate placementDate;
}
