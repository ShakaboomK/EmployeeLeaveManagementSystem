package com.stsk.EmployeeLeaveManagementSystem.service.impl;

import com.stsk.EmployeeLeaveManagementSystem.entity.LeaveType;
import com.stsk.EmployeeLeaveManagementSystem.repository.LeaveTypeRepository;
import com.stsk.EmployeeLeaveManagementSystem.service.LeaveTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LeaveTypeServiceImpl implements LeaveTypeService {

    @Autowired
    private LeaveTypeRepository leaveTypeRepository;

    @Override
    public List<LeaveType> getAllLeaveTypes() {
        return leaveTypeRepository.findAll();
    }
}
