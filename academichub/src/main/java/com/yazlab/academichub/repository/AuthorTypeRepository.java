package com.yazlab.academichub.repository;

import com.yazlab.academichub.entities.AuthorType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorTypeRepository extends JpaRepository<AuthorType, Long> {
}
