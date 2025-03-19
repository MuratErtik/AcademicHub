package com.yazlab.academichub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yazlab.academichub.entities.Department;

public interface DepartmentRepository extends JpaRepository<Department,Long> {
    Department findByDepartmentName(String departmentName);
}
