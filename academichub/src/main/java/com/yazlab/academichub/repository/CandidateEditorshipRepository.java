package com.yazlab.academichub.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.yazlab.academichub.entities.candidateDocuments.CandidateEditorship;

public interface CandidateEditorshipRepository extends JpaRepository<CandidateEditorship,Long> {

    List<CandidateEditorship> findByName(String name);

    @Query(value = "select * from candidate_editorship where candidate_editorship_id = :candidateEditorshipId and application_id = :applicationId", nativeQuery = true)
    CandidateEditorship findByNameAndApplicationId(@Param("candidateEditorshipId") Long candidateEditorshipId, @Param("applicationId") Long applicationId);
}
