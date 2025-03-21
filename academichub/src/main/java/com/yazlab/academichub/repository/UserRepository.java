package com.yazlab.academichub.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yazlab.academichub.domain.USER_ROLE;
import com.yazlab.academichub.entities.User;

public interface UserRepository extends JpaRepository<User,Long> {
    
    User findByEmail(String email);

    User findByTcNo(String tcNo);

    List<User>  findByUserRole(USER_ROLE role);
}
