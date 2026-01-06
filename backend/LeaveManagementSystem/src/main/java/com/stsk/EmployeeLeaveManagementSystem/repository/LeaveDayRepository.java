package com.stsk.EmployeeLeaveManagementSystem.repository;

import com.stsk.EmployeeLeaveManagementSystem.entity.LeaveDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LeaveDayRepository extends JpaRepository<LeaveDay, Long> {

    List<LeaveDay> findByLeaveRequestEmployeeIdAndDate(Long employeeId, LocalDate date);
}
