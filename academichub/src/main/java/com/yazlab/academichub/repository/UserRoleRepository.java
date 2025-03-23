package com.yazlab.academichub.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.yazlab.academichub.entities.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole,Long> {
    UserRole findUserRoleByUserRole(String userRole);
    
}
