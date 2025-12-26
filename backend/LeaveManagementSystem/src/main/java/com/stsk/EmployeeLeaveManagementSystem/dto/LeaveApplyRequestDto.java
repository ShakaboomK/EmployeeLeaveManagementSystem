package com.stsk.EmployeeLeaveManagementSystem.dto;

import com.stsk.EmployeeLeaveManagementSystem.entity.LeaveDay;
import com.stsk.EmployeeLeaveManagementSystem.utils.LeaveDayType;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Schema(description = "Request body for applying leave")
public class LeaveApplyRequestDto {

    @Schema(example ="1", description = "Employee ID")
    @NotNull(message = "employeeId can not be empty or null or negative")
    private Long employeeId;

    @NotNull(message = "leaveTypeId cannot be null or empty or negative")
    @Schema(example = "2", description = "Leave type ID")
    private Long leaveTypeId;

    @NotNull
    @Schema(example = "2025-12-10")
    private LocalDate startDate;

    @NotNull
    @Schema(example = "2025-12-10")
    private LocalDate endDate;


    @ArraySchema(
            schema = @Schema(implementation = LeaveDayTypeDto.class),
            arraySchema = @Schema(
                    description = "Per-day leave breakdown (source of truth)")
    )
    private List<LeaveDayTypeDto> leaveDays;

    @NotBlank(message = "Leave Reason cannot be empty or null")
    @Schema(example = "Personal Reasons MF!")
    private String reason;
}
