package com.management.EmployeeManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.management.EmployeeManagementSystem.model.Department;

@Repository("DepartmentRepository")
public interface DepartmentRepository extends JpaRepository<Department, String> {

}
