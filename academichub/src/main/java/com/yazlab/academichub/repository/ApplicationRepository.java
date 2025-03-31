package com.yazlab.academichub.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.yazlab.academichub.entities.Application;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    @Query(value = "select * from application where user_id = :userId and job_offer_id = :jobOfferId", nativeQuery = true)
    Application findByUserAndJobOffer(@Param("userId") Long userId, @Param("jobOfferId") Long jobOfferId);
}
