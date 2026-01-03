
package com.stsk.EmployeeLeaveManagementSystem.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.stsk.EmployeeLeaveManagementSystem.dto.LeaveDayTypeDto;
import com.stsk.EmployeeLeaveManagementSystem.entity.LeaveDay;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface LeaveDayMapper {

    @Mapping(target = "id", ignore = true)
    // We will set leaveRequest in the parent mapper via @AfterMapping
    LeaveDay toEntity(LeaveDayTypeDto dto);
}
