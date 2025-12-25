package com.stsk.EmployeeLeaveManagementSystem.controller;

import com.stsk.EmployeeLeaveManagementSystem.dto.ErrorResponse;
import com.stsk.EmployeeLeaveManagementSystem.dto.LeaveApplyRequest;
import com.stsk.EmployeeLeaveManagementSystem.dto.LeaveApplyResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/stsk/elms")
@AllArgsConstructor
@Tag(name = "Leave API's",description = "Employee leave Operations")
public class LeaveController {

    @Operation(summary = "Apply for leave")
    @ApiResponses(
            {
                    @ApiResponse(responseCode = "200", description = "Leave applied",
                    content = @Content(schema = @Schema(implementation = LeaveApplyResponse.class))),
                    @ApiResponse(responseCode = "400",description = "Validation Error",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
            }
    )
    @PostMapping("/apply")
    public ResponseEntity<LeaveApplyResponse> applyLeave(@Valid @RequestBody LeaveApplyRequest request) {
        return null;
    }
}
