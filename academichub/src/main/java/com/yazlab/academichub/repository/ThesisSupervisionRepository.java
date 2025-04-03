package com.yazlab.academichub.repository;

import com.yazlab.academichub.entities.ThesisSupervision;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThesisSupervisionRepository extends JpaRepository<ThesisSupervision, Long> {
}