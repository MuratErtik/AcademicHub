package com.yazlab.academichub.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yazlab.academichub.entities.candidateDocuments.CandidateArticle;

public interface CandidateArticleRepository extends JpaRepository<CandidateArticle,Long> {

    List<CandidateArticle> findByArticleName(String articleName);


}
