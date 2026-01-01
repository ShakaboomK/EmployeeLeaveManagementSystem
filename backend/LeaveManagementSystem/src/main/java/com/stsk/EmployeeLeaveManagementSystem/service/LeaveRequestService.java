package com.stsk.EmployeeLeaveManagementSystem.service;

import com.stsk.EmployeeLeaveManagementSystem.dto.LeaveApplyRequest;
import com.stsk.EmployeeLeaveManagementSystem.dto.LeaveApplyResponse;

public interface LeaveRequestService {

    LeaveApplyResponse applyLeave(LeaveApplyRequest request);
}
