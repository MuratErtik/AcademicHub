package com.yazlab.academichub.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.yazlab.academichub.entities.candidateDocuments.CandidateArticle;

public interface CandidateArticleRepository extends JpaRepository<CandidateArticle,Long> {

    List<CandidateArticle> findByArticleName(String articleName);

    @Query(value = "select * from candidate_article where candidate_article_id = :candidateArticleId and application_id = :applicationId", nativeQuery = true)
    CandidateArticle findByArticleNameAndApplicationId(@Param("candidateArticleId") Long candidateArticleId, @Param("applicationId") Long applicationId);



}
