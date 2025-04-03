package com.yazlab.academichub.repository;

import com.yazlab.academichub.entities.ContributionActivities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContributionActivitiesRepository extends JpaRepository<ContributionActivities, Long> {
}
