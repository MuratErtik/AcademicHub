package com.yazlab.academichub.repository;

import com.yazlab.academichub.entities.ScientificMeetingActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeetingActivityRepository extends JpaRepository<ScientificMeetingActivity, Long> {
}
