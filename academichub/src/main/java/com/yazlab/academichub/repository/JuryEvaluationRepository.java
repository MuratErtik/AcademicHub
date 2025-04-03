package com.yazlab.academichub.repository;

import com.yazlab.academichub.entities.JuryEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JuryEvaluationRepository extends JpaRepository<JuryEvaluation, Long> {
}