package com.yazlab.academichub.repository;

import com.yazlab.academichub.entities.CandidateDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateDocumentRepository extends JpaRepository<CandidateDocument, Long> {
}
