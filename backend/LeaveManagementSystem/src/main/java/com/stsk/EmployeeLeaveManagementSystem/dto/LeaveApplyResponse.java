package com.stsk.EmployeeLeaveManagementSystem.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Leave application response, subject to change")
public class LeaveApplyResponse {
    @Schema(example = "1")
    private Long leaveId;

    @Schema(example = "PENDING")
    private String status;

    @Schema(example = "0.5")
    private double totalDays;

    @Schema(example = "Leave applied successfully")
    private String message;
}
