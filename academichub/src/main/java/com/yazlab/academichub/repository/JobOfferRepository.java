package com.yazlab.academichub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yazlab.academichub.entities.JobOffer;

public interface JobOfferRepository extends JpaRepository<JobOffer,Long>{
    
}
