package com.yazlab.academichub.repository;

import com.yazlab.academichub.entities.ArticleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleTypeRepository extends JpaRepository<ArticleType, Long> {
}
