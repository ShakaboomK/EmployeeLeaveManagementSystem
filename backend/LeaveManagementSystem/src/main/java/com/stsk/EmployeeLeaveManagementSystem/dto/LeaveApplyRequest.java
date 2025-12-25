package com.stsk.EmployeeLeaveManagementSystem.dto;

import com.stsk.EmployeeLeaveManagementSystem.utils.HalfDaySessionType;
import com.stsk.EmployeeLeaveManagementSystem.utils.LeaveDurationType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
@Schema(description = "Request body for applying leave")
public class LeaveApplyRequest {

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

    @Schema(example = "HALF_DAY")
    private LeaveDurationType leaveDurationType;

    @Schema(example = "MORNING", description = "if halfday is selected then and only this field is required")
    private HalfDaySessionType  halfDaySessionType;

    @NotBlank(message = "Leave Reason cannot be empty or null")
    @Schema(example = "Personal Reasons MF!")
    private String reason;
}
