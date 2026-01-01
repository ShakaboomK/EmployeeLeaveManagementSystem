package com.stsk.EmployeeLeaveManagementSystem.service;

import com.stsk.EmployeeLeaveManagementSystem.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> getAllUsers();

    Optional<User> getUserByEmail(String email);
}
