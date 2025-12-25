package com.stsk.EmployeeLeaveManagementSystem.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "Standard API error response")
public class ErrorResponse {
    @Schema(example = "400")
    private int status;

    @Schema(example = "Invalid leave type")
    private String errorMessage;

    @Schema(example = "2025-12-10T10:15:30")
    private LocalDateTime timeStamp;
}
