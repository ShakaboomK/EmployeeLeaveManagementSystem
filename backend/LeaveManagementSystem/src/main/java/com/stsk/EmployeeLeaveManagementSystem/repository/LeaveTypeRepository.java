package com.stsk.EmployeeLeaveManagementSystem.repository;

import com.stsk.EmployeeLeaveManagementSystem.entity.LeaveType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaveTypeRepository extends JpaRepository<LeaveType, Long> {

}
