
package com.stsk.EmployeeLeaveManagementSystem.mapper;

import org.mapstruct.Mapper;
import com.stsk.EmployeeLeaveManagementSystem.entity.Employee;
import com.stsk.EmployeeLeaveManagementSystem.entity.LeaveType;
import com.stsk.EmployeeLeaveManagementSystem.utils.LeaveStatus;

@Mapper(componentModel = "spring")
public interface IdRefMapper {

    default Employee toEmployee(Long id) {
        return id == null ? null : new Employee(id); // uses id-only constructor
    }

    default LeaveType toLeaveType(Long id) {
        return id == null ? null : new LeaveType(id); // uses id-only constructor
    }

    default String toString(LeaveStatus status) {
        return status == null ? null : status.name();
    }
}
