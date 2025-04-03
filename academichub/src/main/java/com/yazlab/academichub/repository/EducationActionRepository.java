package com.yazlab.academichub.repository;

import com.yazlab.academichub.entities.EducationAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EducationActionRepository extends JpaRepository<EducationAction, Long> {
}
