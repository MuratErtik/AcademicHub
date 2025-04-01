package com.yazlab.academichub.repository;

import com.yazlab.academichub.entities.AuthorType;

public interface AuthorTypeRepository {

    AuthorType findByAuthorTypeName(String name);
}
