package com.stsk.EmployeeLeaveManagementSystem.controller;

import com.stsk.EmployeeLeaveManagementSystem.dto.LeaveApplyRequest;
import com.stsk.EmployeeLeaveManagementSystem.dto.LeaveApplyResponse;
import com.stsk.EmployeeLeaveManagementSystem.entity.Employee;
import com.stsk.EmployeeLeaveManagementSystem.entity.LeaveRequest;
import com.stsk.EmployeeLeaveManagementSystem.entity.LeaveType;
import com.stsk.EmployeeLeaveManagementSystem.mapper.LeaveRequestMapper;
import com.stsk.EmployeeLeaveManagementSystem.service.LeaveRequestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.temporal.ChronoUnit;

@RestController
@RequestMapping("/api/leaves")
@RequiredArgsConstructor
public class LeaveRequestController {

    @Autowired
    private LeaveRequestService leaveRequestService;

//    private final LeaveRequestMapper leaveRequestMapper;
//
//    public LeaveRequestController(LeaveRequestMapper leaveRequestMapper) {
//        this.leaveRequestMapper = leaveRequestMapper;
//    }

    @PostMapping("/apply")
    public ResponseEntity<LeaveApplyResponse> applyLeave(
            @Validated @RequestBody LeaveApplyRequest request) {

        LeaveApplyResponse response = leaveRequestService.applyLeave(request);

        return ResponseEntity.status(201).body(response);
    }
}
