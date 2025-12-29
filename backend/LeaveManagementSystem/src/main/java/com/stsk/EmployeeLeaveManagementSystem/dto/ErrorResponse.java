package com.stsk.EmployeeLeaveManagementSystem.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Standard API error response")
public class ErrorResponse {
    @Schema(example = "400")
    private int status;

    @Schema(example = "Invalid leave type")
    private String errorMessage;

    @Schema(example = "2025-12-10T10:15:30")
    private LocalDateTime timeStamp;
}
