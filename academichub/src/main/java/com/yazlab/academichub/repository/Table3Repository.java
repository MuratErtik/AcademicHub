package com.yazlab.academichub.repository;

import com.yazlab.academichub.entities.Table3Action;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Table3Repository extends JpaRepository<Table3Action, Long> {
}