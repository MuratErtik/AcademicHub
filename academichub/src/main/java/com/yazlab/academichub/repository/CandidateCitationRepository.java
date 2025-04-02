package com.yazlab.academichub.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.yazlab.academichub.entities.candidateDocuments.CandidateCitation;

public interface CandidateCitationRepository extends JpaRepository<CandidateCitation,Long> {

    List<CandidateCitation> findByArticleToCitation(String citation);

    @Query(value = "select * from candidate_citation where candidate_citation_id = :candidateCitationId and application_id = :applicationId", nativeQuery = true)
    CandidateCitation findByCitationNameAndApplicationId(@Param("candidateCitationId") Long candidateCitationId, @Param("applicationId") Long applicationId);
}
