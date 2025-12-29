package com.stsk.EmployeeLeaveManagementSystem.controller;

import com.stsk.EmployeeLeaveManagementSystem.dto.LeaveApplyRequest;
import com.stsk.EmployeeLeaveManagementSystem.dto.LeaveApplyResponse;
import com.stsk.EmployeeLeaveManagementSystem.entity.Employee;
import com.stsk.EmployeeLeaveManagementSystem.entity.LeaveRequest;
import com.stsk.EmployeeLeaveManagementSystem.entity.LeaveType;
import com.stsk.EmployeeLeaveManagementSystem.mapper.LeaveRequestMapper;
import com.stsk.EmployeeLeaveManagementSystem.repository.EmployeeRepository;
import com.stsk.EmployeeLeaveManagementSystem.repository.LeaveRequestRepository;
import com.stsk.EmployeeLeaveManagementSystem.repository.LeaveTypeRepository;
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
    private LeaveRequestRepository leaveRequestRepository;
    @Autowired private EmployeeRepository employeeRepository;
    @Autowired private LeaveTypeRepository leaveTypeRepository;
//    private final LeaveRequestMapper leaveRequestMapper;

//    public LeaveRequestController(LeaveRequestMapper leaveRequestMapper) {
//        this.leaveRequestMapper = leaveRequestMapper;
//    }
    @PostMapping("/apply")
    public ResponseEntity<LeaveApplyResponse> applyLeave(
            @Validated @RequestBody LeaveApplyRequest request) {

        Employee employee = employeeRepository.findById(request.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        LeaveType leaveType = leaveTypeRepository.findById(request.getLeaveTypeId())
                .orElseThrow(() -> new RuntimeException("Leave type not found"));

        double totalDays = ChronoUnit.DAYS.between(
                request.getStartDate(),
                request.getEndDate()
        ) + 1;

        if (request.getLeaveDurationType() != null
                && request.getLeaveDurationType().name().equals("HALF_DAY")) {
            totalDays = 0.5;
        }

        LeaveRequest leaveRequest = new LeaveRequest();
        leaveRequest.setEmployee(employee);
        leaveRequest.setLeaveType(leaveType);
        leaveRequest.setStartDate(request.getStartDate());
        leaveRequest.setEndDate(request.getEndDate());
        leaveRequest.setLeaveDurationType(request.getLeaveDurationType());
        leaveRequest.setHalfDaySessionType(request.getHalfDaySessionType());
        leaveRequest.setReason(request.getReason());
        leaveRequest.setTotalDays(totalDays);
        leaveRequest.setStatus("PENDING");

        LeaveRequest savedLeave = leaveRequestRepository.save(leaveRequest);

        LeaveApplyResponse response = new LeaveApplyResponse();
        response.setLeaveId(savedLeave.getId());
        response.setStatus(savedLeave.getStatus());
        response.setTotalDays(savedLeave.getTotalDays());
        response.setMessage("Leave applied successfully");


//        LeaveRequest leaveRequest =
//                leaveRequestMapper.toEntity(request, employee, leaveType, totalDays);
//
//        LeaveRequest savedLeave = leaveRequestRepository.save(leaveRequest);
//
//        LeaveApplyResponse response =
//                leaveRequestMapper.toResponse(savedLeave);
        return ResponseEntity.status(201).body(response);
    }
}
