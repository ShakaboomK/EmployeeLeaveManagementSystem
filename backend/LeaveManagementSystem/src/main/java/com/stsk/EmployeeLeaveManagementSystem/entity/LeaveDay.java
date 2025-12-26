package com.stsk.EmployeeLeaveManagementSystem.entity;

import com.stsk.EmployeeLeaveManagementSystem.utils.LeaveDayType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name ="leave_days")
@Getter@Setter@NoArgsConstructor
public class LeaveDay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "leave_request_id",nullable = false)
    private LeaveRequest leaveRequest;

    private LocalDate date;

    private LeaveDayType dayType;

}
