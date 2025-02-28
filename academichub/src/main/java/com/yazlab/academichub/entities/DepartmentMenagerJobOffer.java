package com.yazlab.academichub.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class DepartmentMenagerJobOffer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) 
    private User departmentMenager;

    @ManyToOne
    @JoinColumn(name = "job_offer_id",nullable = false)
    private JobOffer jobOffer;
}
