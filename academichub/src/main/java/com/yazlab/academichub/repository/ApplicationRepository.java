package com.yazlab.academichub.repository;

import com.yazlab.academichub.entities.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    @Query(value = "select * from application where user_id = :userId and job_offer_id = :jobOfferId", nativeQuery = true)
    Application findByUserAndJobOffer(@Param("userId") Long userId, @Param("jobOfferId") Long jobOfferId);

    Application findByApplicationId(Long applicationId);

    @Query("SELECT a FROM Application a " +
           "JOIN FETCH a.articles ca " +
           "LEFT JOIN FETCH ca.authors auth " +
           "LEFT JOIN FETCH auth.authorType " +
           "WHERE a.applicationId = :applicationId")
    Application findWithArticlesAndAuthorsByApplicationId(@Param("applicationId") Long applicationId);
}