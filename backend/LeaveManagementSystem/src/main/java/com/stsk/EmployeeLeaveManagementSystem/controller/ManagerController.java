package com.stsk.EmployeeLeaveManagementSystem.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stsk/elms/manager")
public class ManagerController {

    @GetMapping("/leaves/pending")
    public ResponseEntity<?> getPendingLeaveList(){
        return null;
    }

    @PutMapping("/leaves/{id}/approve")
    public ResponseEntity<?> approveLeave(@PathVariable Long id){return null;}

    @PutMapping("/leaves/{id}/reject")
    public ResponseEntity<?> rejectLeave(@PathVariable Long id){return null;}


}
