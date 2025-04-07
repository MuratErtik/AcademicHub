package com.yazlab.academichub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.yazlab.academichub.entities.JuryApplication;

public interface JuryApplicationRepository extends JpaRepository<JuryApplication,Long>{


    @Query(value = "select * from jury_application where application_id = :applicationId and user_id = :userId", nativeQuery = true)
    JuryApplication findByJuryIdAndApplicationId(@Param("applicationId") Long applicationId, @Param("userId") Long userId);
}
