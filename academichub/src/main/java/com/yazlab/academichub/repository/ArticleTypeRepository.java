package com.yazlab.academichub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yazlab.academichub.entities.ArticleType;

public interface ArticleTypeRepository extends JpaRepository<ArticleType,Long>{

    ArticleType findByArticleTypeName(String articleTypeName);
}
