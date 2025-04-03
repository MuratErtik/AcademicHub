package com.yazlab.academichub.repository;

import com.yazlab.academichub.entities.FacultyGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacultyGroupRepository extends JpaRepository<FacultyGroup, Long> {
}