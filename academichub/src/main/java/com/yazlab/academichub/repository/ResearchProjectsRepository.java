package com.yazlab.academichub.repository;

import com.yazlab.academichub.entities.ResearchProjects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResearchProjectsRepository extends JpaRepository<ResearchProjects, Long> {
}
