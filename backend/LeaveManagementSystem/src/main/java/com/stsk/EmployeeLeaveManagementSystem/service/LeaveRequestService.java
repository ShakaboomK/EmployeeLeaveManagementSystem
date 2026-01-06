package com.stsk.EmployeeLeaveManagementSystem.service;

import com.stsk.EmployeeLeaveManagementSystem.dto.LeaveApplyRequestDto;
import com.stsk.EmployeeLeaveManagementSystem.dto.LeaveApplyResponseDto;
import com.stsk.EmployeeLeaveManagementSystem.dto.LeaveDayTypeDto;
import com.stsk.EmployeeLeaveManagementSystem.entity.Employee;
import com.stsk.EmployeeLeaveManagementSystem.entity.LeaveDay;
import com.stsk.EmployeeLeaveManagementSystem.entity.LeaveRequest;
import com.stsk.EmployeeLeaveManagementSystem.entity.LeaveType;
import com.stsk.EmployeeLeaveManagementSystem.repository.EmployeeRepository;
import com.stsk.EmployeeLeaveManagementSystem.repository.LeaveDayRepository;
import com.stsk.EmployeeLeaveManagementSystem.repository.LeaveRequestRepository;
import com.stsk.EmployeeLeaveManagementSystem.repository.LeaveTypeRepository;
import com.stsk.EmployeeLeaveManagementSystem.utils.LeaveDayType;
import com.stsk.EmployeeLeaveManagementSystem.utils.LeaveStatus;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LeaveRequestService {

    private final LeaveDayRepository leaveDayRepository;
    private final EmployeeRepository employeeRepository;
    private final LeaveTypeRepository leaveTypeRepository;
    private final LeaveRequestRepository leaveRequestRepository;



    public LeaveApplyResponseDto  applyLeaveOrReject(LeaveApplyRequestDto request){
        Optional<Employee> e = employeeRepository.findById(request.getEmployeeId());
        Employee employee = e.orElse(null);

        for( LeaveDayTypeDto requestedDay :request.getLeaveDays()){
            List<LeaveDay> existingLeaveDays = leaveDayRepository
                    .findByLeaveRequestEmployeeIdAndDate(request.getEmployeeId(), requestedDay.getDate());

            for(LeaveDay existing:existingLeaveDays){
                if(isOverlap(existing.getDayType(),requestedDay.getDayType())){
                    throw  new IllegalStateException("Leave already exits on "+requestedDay.getDate()
                    +" for session "+ existing.getDayType());
                }
            }
        }

        LeaveRequest leaveRequest = new LeaveRequest();
        leaveRequest.setEmployee(employee);
        LeaveType leaveType =leaveTypeRepository.findById(request.getLeaveTypeId()).orElse(null);
        leaveRequest.setLeaveType(leaveType);
        leaveRequest.setStartDate(request.getStartDate());
        leaveRequest.setEndDate(request.getEndDate());
        leaveRequest.setStatus(LeaveStatus.PENDING);

        List<LeaveDay> leaveDays = new ArrayList<>();
        double totalDays = 0;
        for(LeaveDayTypeDto dto: request.getLeaveDays()){
            LeaveDay leaveDay = new LeaveDay();
            leaveDay.setDate(dto.getDate());
            leaveDay.setDayType(dto.getDayType());
            leaveDay.setLeaveRequest(leaveRequest);

            leaveDays.add(leaveDay);
            totalDays+=(dto.getDayType()== LeaveDayType.FULL_DAY)?1:0.5;
        }
        leaveRequest.setTotalDays(totalDays);
        leaveRequest.setLeaveDays(leaveDays);
        leaveRequest.setManagerComment("leave approval pending from manager");

        LeaveRequest savedLeaveRequest = leaveRequestRepository.save(leaveRequest);
        LeaveApplyResponseDto leaveApplyResponseDto = new LeaveApplyResponseDto();
        leaveApplyResponseDto.setLeaveId(leaveRequest.getId());
        leaveApplyResponseDto.setStatus(leaveRequest.getStatus().toString());
        leaveApplyResponseDto.setTotalDays(totalDays);
        leaveApplyResponseDto.setMessage("Leave Applied Successfully, waiting for RM Action");
        // pending setters
        return leaveApplyResponseDto;
    }

    private static boolean isOverlap(LeaveDayType existing, LeaveDayType requested){
        if(existing == LeaveDayType.FULL_DAY  ||requested == LeaveDayType.FULL_DAY){
            return true;
        }
        return existing == requested;
    }

}
