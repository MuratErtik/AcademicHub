package com.yazlab.academichub.repository;

import com.yazlab.academichub.entities.SupportingDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupportingDocumentRepository extends JpaRepository<SupportingDocument, Long> {
}