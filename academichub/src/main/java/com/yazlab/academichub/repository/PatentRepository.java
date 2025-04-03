package com.yazlab.academichub.repository;

import com.yazlab.academichub.entities.Patent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatentRepository extends JpaRepository<Patent, Long> {
}