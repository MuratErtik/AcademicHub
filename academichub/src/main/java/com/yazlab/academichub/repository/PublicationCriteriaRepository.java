package com.yazlab.academichub.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.yazlab.academichub.dto.PublicationCriteriaDTO;
import com.yazlab.academichub.entities.PublicationCriteria;

public interface PublicationCriteriaRepository extends JpaRepository<PublicationCriteria,Long>{

    @Query(value = "SELECT p.publication_criteria_id as publicationCriteriaId, " +
               "p.article_count as articleCount, " +
               "t.action_name as actionName " +
               "FROM publication_criteria p " +
               "LEFT JOIN table3action t ON p.table3action_id = t.table3action_id " +
               "WHERE t.position = :positionId", nativeQuery = true)
List<PublicationCriteriaDTO> findAllWithActionDetails(@Param("positionId") Long positionId);
}
