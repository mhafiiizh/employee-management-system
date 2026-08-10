package com.hafizh.ems.service.impl;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hafizh.ems.dto.request.user.LoginRequest;
import com.hafizh.ems.dto.request.user.RegisterRequest;
import com.hafizh.ems.dto.response.UserResponse;
import com.hafizh.ems.entity.Role;
import com.hafizh.ems.entity.User;
import com.hafizh.ems.exception.BadRequestException;
import com.hafizh.ems.exception.DuplicateResourceException;
import com.hafizh.ems.mapper.UserMapper;
import com.hafizh.ems.repository.UserRepository;
import com.hafizh.ems.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    @Override
    public UserResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException("Email already exists");
        }
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new BadRequestException("Password and Confirm Password do not match");
        }

        String encodedPassword = passwordEncoder.encode(request.getPassword());

        User user = userMapper.toCreateEntity(request, Role.USER, encodedPassword);
        User userSaved = userRepository.save(user);
        return userMapper.toResponse(userSaved);
    }

    @Override
    public String login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new BadRequestException("Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadRequestException("Invalid email or password");
        }
        // Generate and return a token (this is just a placeholder, implement your own
        // token generation logic)
        return "dummy-token";
    }

}
