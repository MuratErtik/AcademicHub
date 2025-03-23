package com.yazlab.academichub.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.yazlab.academichub.domain.USER_ROLE;
import com.yazlab.academichub.entities.Department;
import com.yazlab.academichub.entities.User;

public interface UserRepository extends JpaRepository<User,Long> {
    
    User findByEmail(String email);

    User findByTcNo(String tcNo);

    List<User>  findByUserRole(USER_ROLE role);

    List<User> findByDepartment(Department department);

    @Query(value = "select * from users where user_role = :role and department_id = :departmentId", nativeQuery = true)
    List<User>  findByUserRoleAndDepartment(@Param("role") USER_ROLE role, @Param("departmentId") Long departmentId);
}
