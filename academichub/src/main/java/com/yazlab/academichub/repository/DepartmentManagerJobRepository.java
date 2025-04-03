package com.yazlab.academichub.repository;

import com.yazlab.academichub.entities.DepartmentMenagerJobOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentManagerJobRepository extends JpaRepository<DepartmentMenagerJobOffer, Long> {
}
