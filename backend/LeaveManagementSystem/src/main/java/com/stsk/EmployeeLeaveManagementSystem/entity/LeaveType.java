package com.stsk.EmployeeLeaveManagementSystem.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity@Table(name = "leave_types")
@Getter@Setter@NoArgsConstructor
public class LeaveType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String typeName; // Sick, Casual, Paid

    @Column(nullable = false)
    private int maxDaysPerYear;

    @OneToMany(mappedBy = "leaveType")
    private List<LeaveRequest> leaveRequests;

}
