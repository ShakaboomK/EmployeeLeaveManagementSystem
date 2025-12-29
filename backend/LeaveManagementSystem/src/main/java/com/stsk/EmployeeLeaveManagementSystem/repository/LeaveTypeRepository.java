package com.stsk.EmployeeLeaveManagementSystem.repository;

import com.stsk.EmployeeLeaveManagementSystem.entity.LeaveType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaveTypeRepository extends JpaRepository<LeaveType, Long> {
}
