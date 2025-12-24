package com.stsk.EmployeeLeaveManagementSystem.entity;

import com.stsk.EmployeeLeaveManagementSystem.utils.HalfDaySessionType;
import com.stsk.EmployeeLeaveManagementSystem.utils.LeaveDurationType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "leave_requests")
@Getter@Setter@NoArgsConstructor
public class LeaveRequest {

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
