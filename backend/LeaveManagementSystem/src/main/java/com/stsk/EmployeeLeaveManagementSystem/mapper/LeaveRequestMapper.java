package com.stsk.EmployeeLeaveManagementSystem.mapper;

import com.stsk.EmployeeLeaveManagementSystem.dto.LeaveApplyRequest;
import com.stsk.EmployeeLeaveManagementSystem.dto.LeaveApplyResponse;
import com.stsk.EmployeeLeaveManagementSystem.entity.Employee;
import com.stsk.EmployeeLeaveManagementSystem.entity.LeaveRequest;
import com.stsk.EmployeeLeaveManagementSystem.entity.LeaveType;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
//@Mapper
//@Component
public interface LeaveRequestMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "employee", source = "employee")
    @Mapping(target = "leaveType", source = "leaveType")
    @Mapping(target = "status", constant = "PENDING")
    @Mapping(target = "totalDays", source = "totalDays")
    @Mapping(target = "appliedDate", expression = "java(java.time.LocalDateTime.now())")
    LeaveRequest toEntity(
            LeaveApplyRequest request,
            Employee employee,
            LeaveType leaveType,
            double totalDays
    );

    @Mapping(target = "leaveId", source = "id")
    @Mapping(target = "message", constant = "Leave applied successfully")
    LeaveApplyResponse toResponse(LeaveRequest leaveRequest);
}
