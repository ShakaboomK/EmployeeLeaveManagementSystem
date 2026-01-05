package com.stsk.EmployeeLeaveManagementSystem.repository;

import com.stsk.EmployeeLeaveManagementSystem.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
