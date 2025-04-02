package com.yazlab.academichub.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.yazlab.academichub.entities.candidateDocuments.CandidateEducationAction;

public interface CandidateEducationActionRepository extends JpaRepository<CandidateEducationAction,Long> {

    List<CandidateEducationAction> findByLectureName(String lectureName);

    @Query(value = "select * from candidate_education_action where candidate_education_action_id = :candidateEducationActionId and application_id = :applicationId", nativeQuery = true)
    CandidateEducationAction findByLectureNameAndApplicationId(@Param("candidateEducationActionId") Long candidateEducationActionId, @Param("applicationId") Long applicationId);
}
