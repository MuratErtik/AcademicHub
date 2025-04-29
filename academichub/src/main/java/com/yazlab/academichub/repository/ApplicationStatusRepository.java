package com.yazlab.academichub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yazlab.academichub.entities.ApplicationStatus;

public interface ApplicationStatusRepository extends JpaRepository<ApplicationStatus,Long> {

    ApplicationStatus findByApplicationStatus(String name);
}
