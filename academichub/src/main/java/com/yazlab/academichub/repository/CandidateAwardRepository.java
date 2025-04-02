package com.yazlab.academichub.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.yazlab.academichub.entities.candidateDocuments.CandidateAward;

public interface CandidateAwardRepository  extends JpaRepository<CandidateAward,Long>{


    @Query(value = "select * from candidate_award where candidate_award_id = :candidateAwardId and application_id = :applicationId", nativeQuery = true)
    CandidateAward findByAwardIdAndApplicationId(@Param("candidateAwardId") Long candidateAwardId, @Param("applicationId") Long applicationId);
}
