package com.stsk.EmployeeLeaveManagementSystem.dto;

import com.stsk.EmployeeLeaveManagementSystem.utils.LeaveDayType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
@Schema(description = "type of leave for that leave day")
public class LeaveDayTypeDto {
    @Schema(example = "2025-12-10")
    @NotNull(message = "Date is required or invalid date")
    private LocalDate date;

    @NotNull(message = "leave day type is required or invalid")
    @Schema(example = "HALF_DAY_FORENOON")
    private LeaveDayType dayType;

}
