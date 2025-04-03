package com.yazlab.academichub.repository;

import com.yazlab.academichub.entities.Prerequisites;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrerequisitesRepository extends JpaRepository<Prerequisites, Long> {
}
