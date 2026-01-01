package com.stsk.EmployeeLeaveManagementSystem.service.impl;

import com.stsk.EmployeeLeaveManagementSystem.entity.User;
import com.stsk.EmployeeLeaveManagementSystem.repository.UserRepository;
import com.stsk.EmployeeLeaveManagementSystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
