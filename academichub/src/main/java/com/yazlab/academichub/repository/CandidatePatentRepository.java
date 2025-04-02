package com.yazlab.academichub.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.yazlab.academichub.entities.candidateDocuments.CandidatePatent;

public interface CandidatePatentRepository extends JpaRepository<CandidatePatent,Long>{

    List<CandidatePatent> findByPatentName(String sma);

    @Query(value = "select * from candidate_patent where candidate_patent_id = :candidatePatentId and application_id = :applicationId", nativeQuery = true)
    CandidatePatent findByPatentNameAndApplicationId(@Param("candidatePatentId") Long candidatePatentId, @Param("applicationId") Long applicationId);
}
