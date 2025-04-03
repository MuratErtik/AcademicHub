package com.yazlab.academichub.repository;

import com.yazlab.academichub.entities.ImportantThingsToFillTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImportantThingsRepository extends JpaRepository<ImportantThingsToFillTable, Long> {
}