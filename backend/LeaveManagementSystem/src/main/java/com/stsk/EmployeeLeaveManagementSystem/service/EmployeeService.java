package com.stsk.EmployeeLeaveManagementSystem.service;

import com.stsk.EmployeeLeaveManagementSystem.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> getAllEmployees();

    Employee getEmployeeById(Long id);
}
