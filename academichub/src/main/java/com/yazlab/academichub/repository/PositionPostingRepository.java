package com.yazlab.academichub.repository;

import com.yazlab.academichub.entities.PositionPosting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionPostingRepository extends JpaRepository<PositionPosting, Long> {
}