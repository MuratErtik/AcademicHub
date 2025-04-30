package com.yazlab.academichub.repository;

import com.yazlab.academichub.entities.Table3Action;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Table3ActionRepository extends JpaRepository<Table3Action, Long> {

    @Query("SELECT DISTINCT t.actionName FROM Table3Action t")
    List<String> findAllActionNames();
}