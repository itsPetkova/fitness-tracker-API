package org.example.service;

import org.example.dto.UserDto;
import org.example.dto.UserResponse;
import org.example.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User registerUser(UserDto user);
    UserResponse getUser(Long id);
}
