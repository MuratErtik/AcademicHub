package com.yazlab.academichub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yazlab.academichub.entities.candidateDocuments.CandidateArticle;

public interface CandidateArticleRepository extends JpaRepository<CandidateArticle,Long> {

    CandidateArticle findByArticleName(String articleName);
}
