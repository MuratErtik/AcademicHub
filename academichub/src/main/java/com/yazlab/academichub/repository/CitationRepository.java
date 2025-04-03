package com.yazlab.academichub.repository;

import com.yazlab.academichub.entities.Citation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CitationRepository extends JpaRepository<Citation, Long> {
}
