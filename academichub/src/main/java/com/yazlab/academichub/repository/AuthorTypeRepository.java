package com.yazlab.academichub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yazlab.academichub.entities.AuthorType;

public interface AuthorTypeRepository extends  JpaRepository<AuthorType,Long> {

    AuthorType findByAuthorTypeName(String name);
}
