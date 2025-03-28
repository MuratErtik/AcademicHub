package com.yazlab.academichub.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.yazlab.academichub.entities.JobOffer;

public interface JobOfferRepository extends JpaRepository<JobOffer,Long>{
    JobOffer findByJobOfferId(Long jobOfferId);


    @Query(value = "select * from job_offer where position = :position", nativeQuery = true)
List<JobOffer> findByPosition(@Param("position") String position);
}
