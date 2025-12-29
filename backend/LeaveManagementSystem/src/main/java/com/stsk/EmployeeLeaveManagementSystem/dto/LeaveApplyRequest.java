package com.stsk.EmployeeLeaveManagementSystem.dto;

import com.stsk.EmployeeLeaveManagementSystem.utils.HalfDaySessionType;
import com.stsk.EmployeeLeaveManagementSystem.utils.LeaveDurationType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

//@Setter
//@Getter
@AllArgsConstructor @NoArgsConstructor
@RequiredArgsConstructor
@Schema(description = "Request body for applying leave")
public class LeaveApplyRequest {

    @Schema(example ="1", description = "Employee ID")
    @NotNull(message = "employeeId can not be empty or null or negative")
    private Long employeeId;

    public void setEmployeeId(@NotNull(message = "employeeId can not be empty or null or negative") Long employeeId) {
        this.employeeId = employeeId;
    }

    public void setLeaveTypeId(@NotNull(message = "leaveTypeId cannot be null or empty or negative") Long leaveTypeId) {
        this.leaveTypeId = leaveTypeId;
    }

    public void setStartDate(@NotNull LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(@NotNull LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setLeaveDurationType(LeaveDurationType leaveDurationType) {
        this.leaveDurationType = leaveDurationType;
    }

    public void setHalfDaySessionType(HalfDaySessionType halfDaySessionType) {
        this.halfDaySessionType = halfDaySessionType;
    }

    public void setReason(@NotBlank(message = "Leave Reason cannot be empty or null") String reason) {
        this.reason = reason;
    }

    public @NotNull(message = "employeeId can not be empty or null or negative") Long getEmployeeId() {
        return employeeId;
    }

    public @NotNull(message = "leaveTypeId cannot be null or empty or negative") Long getLeaveTypeId() {
        return leaveTypeId;
    }

    public @NotNull LocalDate getStartDate() {
        return startDate;
    }

    public @NotNull LocalDate getEndDate() {
        return endDate;
    }

    public LeaveDurationType getLeaveDurationType() {
        return leaveDurationType;
    }

    public HalfDaySessionType getHalfDaySessionType() {
        return halfDaySessionType;
    }

    public @NotBlank(message = "Leave Reason cannot be empty or null") String getReason() {
        return reason;
    }

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
