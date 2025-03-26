package com.yazlab.academichub.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.yazlab.academichub.dto.MinMaxPointCriteriaDTO;
import com.yazlab.academichub.entities.MinMaxPointCriteria;

public interface MinMaxPointCriteriaRepository extends JpaRepository<MinMaxPointCriteria, Long> {

    @Query(value = "SELECT m.min_max_point_criteria_id as minMaxPointCriteriaId, " +
               "m.max_point as maxPoint, m.min_point as minPoint, " +
               "t.action_name as actionName " +
               "FROM min_max_point_criteria m " +
               "LEFT JOIN table3action t ON m.table3action_id = t.table3action_id " +
               "WHERE t.position = :positionId", nativeQuery = true)
List<MinMaxPointCriteriaDTO> findAllWithActionDetails(@Param("positionId") Long positionId);
}
