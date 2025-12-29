package com.stsk.EmployeeLeaveManagementSystem.controller;

import com.stsk.EmployeeLeaveManagementSystem.entity.LeaveType;
import com.stsk.EmployeeLeaveManagementSystem.repository.LeaveTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/leave-types")
@RequiredArgsConstructor
public class LeaveTypeController {

    @Autowired
    private LeaveTypeRepository leaveTypeRepository;

    @GetMapping
    public ResponseEntity<List<LeaveType>> getAllLeaveTypes() {
        return ResponseEntity.ok(leaveTypeRepository.findAll());
    }
}

