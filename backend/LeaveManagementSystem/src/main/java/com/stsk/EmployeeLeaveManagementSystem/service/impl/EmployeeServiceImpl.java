package com.stsk.EmployeeLeaveManagementSystem.service.impl;

import com.stsk.EmployeeLeaveManagementSystem.entity.Employee;
import com.stsk.EmployeeLeaveManagementSystem.repository.EmployeeRepository;
import com.stsk.EmployeeLeaveManagementSystem.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElse(null);
    }
}
