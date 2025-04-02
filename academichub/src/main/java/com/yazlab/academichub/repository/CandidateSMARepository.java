package com.yazlab.academichub.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.yazlab.academichub.entities.candidateDocuments.CandidateSMA;

public interface CandidateSMARepository extends JpaRepository<CandidateSMA,Long> {

    List<CandidateSMA> findBySmaName(String sma);

    @Query(value = "select * from candidatesma where candidatesmaid = :candidateSmaId and application_id = :applicationId", nativeQuery = true)
    CandidateSMA findBySmaNameAndApplicationId(@Param("candidateSmaId") Long candidateSmaId, @Param("applicationId") Long applicationId);
}
