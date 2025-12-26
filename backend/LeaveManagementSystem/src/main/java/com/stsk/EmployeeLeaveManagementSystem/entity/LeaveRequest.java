package com.stsk.EmployeeLeaveManagementSystem.entity;

import com.stsk.EmployeeLeaveManagementSystem.utils.LeaveStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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

    @OneToMany(mappedBy = "leaveRequest", cascade = CascadeType.PERSIST)
    private List<LeaveDay> leaveDays;

    @Column(length = 500)
    private String reason;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private LeaveStatus status; // PENDING, APPROVED, REJECTED

    private String managerComment;

    private LocalDateTime appliedDate = LocalDateTime.now();

}
