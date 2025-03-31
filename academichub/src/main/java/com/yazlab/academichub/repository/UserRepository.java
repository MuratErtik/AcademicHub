package com.yazlab.academichub.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.yazlab.academichub.entities.Department;
import com.yazlab.academichub.entities.User;
import com.yazlab.academichub.entities.UserRole;

public interface UserRepository extends JpaRepository<User,Long> {
    
    User findByEmail(String email);

    User findByTcNo(String tcNo);

    List<User>  findByUserRole(UserRole userRole);

    List<User> findByDepartment(Department department);

    @Query(value = "select * from users where role_id = :roleId and department_id = :departmentId", nativeQuery = true)
    List<User>  findByUserRoleAndDepartment(@Param("roleId") Long roleId, @Param("departmentId") Long departmentId);

    User findByUserId(Long userId);
}
