package com.yazlab.academichub.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yazlab.academichub.entities.candidateDocuments.CandidateSMA;

public interface CandidateSMARepository extends JpaRepository<CandidateSMA,Long> {

    List<CandidateSMA> findBySmaName(String sma);
}
