package com.stsk.EmployeeLeaveManagementSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity@Table(name = "leave_types")
//@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LeaveType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getMaxDaysPerYear() {
        return maxDaysPerYear;
    }

    public void setMaxDaysPerYear(int maxDaysPerYear) {
        this.maxDaysPerYear = maxDaysPerYear;
    }

    public List<LeaveRequest> getLeaveRequests() {
        return leaveRequests;
    }

    public void setLeaveRequests(List<LeaveRequest> leaveRequests) {
        this.leaveRequests = leaveRequests;
    }

    @Column(nullable = false, unique = true)
    private String typeName; // Sick, Casual, Paid

    @Column(nullable = false)
    private int maxDaysPerYear;

    @OneToMany(mappedBy = "leaveType")
    private List<LeaveRequest> leaveRequests;

}
