package com.yazlab.academichub.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.yazlab.academichub.entities.candidateDocuments.CandidateThesisSupervision;

public interface CandidateThesisSupervisionRepository extends JpaRepository<CandidateThesisSupervision,Long> {

    List<CandidateThesisSupervision> findByThesisName(String thesisName);

    @Query(value = "select * from candidate_thesis_supervision where candidate_thesis_supervision_id = :candidateThesisId and application_id = :applicationId", nativeQuery = true)
    CandidateThesisSupervision findByThesisNameAndApplicationId(@Param("candidateThesisId") Long candidateThesisId, @Param("applicationId") Long applicationId);

}
