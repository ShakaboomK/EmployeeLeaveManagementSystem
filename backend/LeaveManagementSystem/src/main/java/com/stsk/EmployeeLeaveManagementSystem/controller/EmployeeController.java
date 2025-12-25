package com.stsk.EmployeeLeaveManagementSystem.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stsk/elms/employees")
public class EmployeeController {

    @GetMapping("/me")
    public ResponseEntity<?> getEmployeeDetails(){
        return null;
    }

    @GetMapping("/leaves")
    public ResponseEntity<?> getLeaveHistory(){
        return null;
    }
}
