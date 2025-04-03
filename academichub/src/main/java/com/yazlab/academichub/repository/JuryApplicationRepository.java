package com.yazlab.academichub.repository;

import com.yazlab.academichub.entities.JuryApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JuryApplicationRepository extends JpaRepository<JuryApplication, Long> {
}