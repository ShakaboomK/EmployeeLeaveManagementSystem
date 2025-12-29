package com.stsk.EmployeeLeaveManagementSystem.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

//@Setter
//@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Leave application response, subject to change")
public class LeaveApplyResponse {
    @Schema(example = "1")
    private Long leaveId;

    public Long getLeaveId() {
        return leaveId;
    }

    public void setLeaveId(Long leaveId) {
        this.leaveId = leaveId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTotalDays() {
        return totalDays;
    }

    public void setTotalDays(double totalDays) {
        this.totalDays = totalDays;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Schema(example = "PENDING")
    private String status;

    @Schema(example = "0.5")
    private double totalDays;

    @Schema(example = "Leave applied successfully")
    private String message;
}
