package com.stsk.EmployeeLeaveManagementSystem.entity;

import com.stsk.EmployeeLeaveManagementSystem.utils.HalfDaySessionType;
import com.stsk.EmployeeLeaveManagementSystem.utils.LeaveDurationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "leave_requests")
//@Getter
//@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LeaveRequest {

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public LeaveType getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(LeaveType leaveType) {
        this.leaveType = leaveType;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public double getTotalDays() {
        return totalDays;
    }

    public void setTotalDays(double totalDays) {
        this.totalDays = totalDays;
    }

    public LeaveDurationType getLeaveDurationType() {
        return leaveDurationType;
    }

    public void setLeaveDurationType(LeaveDurationType leaveDurationType) {
        this.leaveDurationType = leaveDurationType;
    }

    public HalfDaySessionType getHalfDaySessionType() {
        return halfDaySessionType;
    }

    public void setHalfDaySessionType(HalfDaySessionType halfDaySessionType) {
        this.halfDaySessionType = halfDaySessionType;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getManagerComment() {
        return managerComment;
    }

    public void setManagerComment(String managerComment) {
        this.managerComment = managerComment;
    }

    public LocalDateTime getAppliedDate() {
        return appliedDate;
    }

    public void setAppliedDate(LocalDateTime appliedDate) {
        this.appliedDate = appliedDate;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "leave_type_id", nullable = false)
    private LeaveType leaveType;

    private LocalDate startDate;
    private LocalDate endDate;

    @Column(nullable = false)
    private double totalDays;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private LeaveDurationType  leaveDurationType;

    @Column(name = "half_day_session")
    @Enumerated(EnumType.STRING)
    private HalfDaySessionType halfDaySessionType;

    @Column(length = 500)
    private String reason;

    @Column(nullable = false)
    private String status; // PENDING, APPROVED, REJECTED

    private String managerComment;

    private LocalDateTime appliedDate = LocalDateTime.now();

}
