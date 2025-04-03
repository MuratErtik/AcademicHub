package com.yazlab.academichub.repository;

import com.yazlab.academichub.entities.PublicationCriteria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicationCriteriaRepository extends JpaRepository<PublicationCriteria, Long> {
}
