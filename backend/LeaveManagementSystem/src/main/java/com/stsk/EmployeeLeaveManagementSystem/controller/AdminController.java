package com.stsk.EmployeeLeaveManagementSystem.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stsk/elms/amdin")
public class AdminController {

    @PostMapping("/leave-types")
    public ResponseEntity<?> createLeaveType(){
        return null;
    }

    @GetMapping("/employees")
    public ResponseEntity<?> getEmployees(){return null;}

}
