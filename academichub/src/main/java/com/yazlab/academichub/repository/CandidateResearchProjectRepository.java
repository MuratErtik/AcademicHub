package com.yazlab.academichub.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.yazlab.academichub.entities.candidateDocuments.CandidateResearchProject;

public interface CandidateResearchProjectRepository extends JpaRepository<CandidateResearchProject,Long> {

    List<CandidateResearchProject> findByName(String name);

    @Query(value = "select * from candidate_research_project where candidate_research_project_id = :candidateResearchProjectId and application_id = :applicationId", nativeQuery = true)
    CandidateResearchProject findByNameAndApplicationId(@Param("candidateResearchProjectId") Long candidateResearchProjectId, @Param("applicationId") Long applicationId);
}
