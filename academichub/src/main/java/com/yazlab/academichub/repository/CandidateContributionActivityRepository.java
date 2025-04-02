package com.yazlab.academichub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.yazlab.academichub.entities.candidateDocuments.CandidateContributionActivity;

public interface CandidateContributionActivityRepository  extends JpaRepository<CandidateContributionActivity,Long>{

    @Query(value = "select * from candidate_contribution_activity where candidate_contribution_activity_id = :contributionId and application_id = :applicationId", nativeQuery = true)
    CandidateContributionActivity findByIdAndApplicationId(@Param("contributionId") Long contributionId, @Param("applicationId") Long applicationId);
}
