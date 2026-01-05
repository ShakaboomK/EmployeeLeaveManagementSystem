package com.stsk.EmployeeLeaveManagementSystem.repository;

import com.stsk.EmployeeLeaveManagementSystem.entity.LeaveDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaveDayRepository extends JpaRepository<LeaveDay, Long> {
}
