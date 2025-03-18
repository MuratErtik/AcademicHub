package com.yazlab.academichub;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yazlab.academichub.entities.User;

public interface UserRepository extends JpaRepository<User,Long> {
    
    User findByEmail(String email);
}
