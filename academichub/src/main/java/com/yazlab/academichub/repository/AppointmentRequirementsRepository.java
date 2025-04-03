package com.yazlab.academichub.repository;

import com.yazlab.academichub.entities.AppointmentRequirements;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRequirementsRepository extends JpaRepository<AppointmentRequirements, Long> {
}
