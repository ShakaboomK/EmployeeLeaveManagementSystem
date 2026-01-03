
package com.stsk.EmployeeLeaveManagementSystem.mapper;

import org.mapstruct.*;
import java.util.List;

import com.stsk.EmployeeLeaveManagementSystem.dto.LeaveApplyRequestDto;
import com.stsk.EmployeeLeaveManagementSystem.dto.LeaveApplyResponseDto;
import com.stsk.EmployeeLeaveManagementSystem.dto.LeaveDayTypeDto;
import com.stsk.EmployeeLeaveManagementSystem.entity.LeaveDay;
import com.stsk.EmployeeLeaveManagementSystem.entity.LeaveRequest;
import com.stsk.EmployeeLeaveManagementSystem.utils.LeaveDayType;

@Mapper(
    componentModel = "spring",
    uses = { IdRefMapper.class, LeaveDayMapper.class },
    unmappedTargetPolicy = ReportingPolicy.ERROR
)
public abstract class LeaveRequestMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "employee", source = "employeeId")      // IdRefMapper
    @Mapping(target = "leaveType", source = "leaveTypeId")     // IdRefMapper
    @Mapping(target = "startDate", source = "startDate")
    @Mapping(target = "endDate", source = "endDate")
    @Mapping(target = "reason", source = "reason")
    @Mapping(target = "leaveDays", source = "leaveDays")       // LeaveDayMapper
    @Mapping(target = "totalDays", expression = "java(calculateTotalDays(dto.getLeaveDays()))")
    @Mapping(target = "status", constant = "PENDING")
    @Mapping(target = "managerComment", ignore = true)
    @Mapping(target = "appliedDate", ignore = true)
    public abstract LeaveRequest toEntity(LeaveApplyRequestDto dto);

    @AfterMapping
    protected void linkChildren(@MappingTarget LeaveRequest entity) {
        if (entity.getLeaveDays() != null) {
            for (LeaveDay d : entity.getLeaveDays()) {
                d.setLeaveRequest(entity);
            }
        }
    }

    public LeaveApplyResponseDto toApplyResponseDto(LeaveRequest entity, String message) {
        LeaveApplyResponseDto dto = new LeaveApplyResponseDto();
        dto.setLeaveId(entity.getId());
        dto.setStatus(entity.getStatus() != null ? entity.getStatus().name() : null);
        dto.setTotalDays(entity.getTotalDays());
        dto.setMessage(message);
        return dto;
    }

    protected double calculateTotalDays(List<LeaveDayTypeDto> days) {
        if (days == null || days.isEmpty()) return 0d;
        double total = 0d;
        for (LeaveDayTypeDto d : days) {
            LeaveDayType type = d.getDayType();
            if (type == null) continue;
            switch (type) {
                case FULL_DAY -> total += 1.0;
                case HALF_DAY_FORENOON, HALF_DAY_AFTERNOON -> total += 0.5;
                default -> { /* extend for new types */ }
            }
        }
        return total;
    }
}
