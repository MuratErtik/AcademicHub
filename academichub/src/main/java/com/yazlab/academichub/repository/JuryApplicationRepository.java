package com.yazlab.academichub.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.yazlab.academichub.entities.JuryApplication;

public interface JuryApplicationRepository extends JpaRepository<JuryApplication,Long>{


    @Query(value = "select * from jury_application where user_id = :juryId and application_id = :applicationId", nativeQuery = true)
    JuryApplication findByJuryIdAndApplicationId(@Param("juryId") Long juryId, @Param("applicationId") Long applicationId);
    

    @Query(value = "select * from jury_application where user_id = :userId", nativeQuery = true)
    Set<JuryApplication> findByUser(@Param("userId") Long userId);
}
