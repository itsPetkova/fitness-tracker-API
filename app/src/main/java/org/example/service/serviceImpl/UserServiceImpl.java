package org.example.service.serviceImpl;

import org.example.dto.AppDto;
import org.example.dto.UserDto;
import org.example.dto.UserResponse;
import org.example.entity.User;
import org.example.exception.NotFoundException;
import org.example.exception.UserAlreadyExistsException;
import org.example.repository.ApplicationRepository;
import org.example.repository.UserRepository;
import org.example.service.UserService;
import org.example.utils.SecurityUtils;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ApplicationRepository applicationRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper,
                           PasswordEncoder passwordEncoder, ApplicationRepository applicationRepository) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.applicationRepository = applicationRepository;
    }

    @Override
    public User registerUser(UserDto userDto) {
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new UserAlreadyExistsException("User with this email already exists.");
        }

        User user = modelMapper.map(userDto, User.class);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public UserResponse getUser(Long id) {
        Long currentUserId = SecurityUtils.getCurrentUserId();
        if (!Objects.equals(currentUserId, id)) {
            throw new AccessDeniedException("Forbidden: you can only access your own user data.");
        }

        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));

        List<AppDto> apps = applicationRepository.findAllByOwnerIdOrderByIdDesc(id)
                .stream()
                .map(app -> modelMapper.map(app, AppDto.class))
                .toList();

        return new UserResponse(user.getId(), user.getEmail(), apps);
    }
}

