package com.hafizh.ems.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hafizh.ems.dto.request.auth.LoginRequest;
import com.hafizh.ems.dto.request.auth.RefreshTokenRequest;
import com.hafizh.ems.dto.request.auth.RegisterRequest;
import com.hafizh.ems.dto.response.LoginResponse;
import com.hafizh.ems.dto.response.UserResponse;
import com.hafizh.ems.entity.Role;
import com.hafizh.ems.entity.User;
import com.hafizh.ems.exception.BadRequestException;
import com.hafizh.ems.exception.DuplicateResourceException;
import com.hafizh.ems.exception.AuthenticationException;
import com.hafizh.ems.mapper.UserMapper;
import com.hafizh.ems.repository.UserRepository;
import com.hafizh.ems.security.JwtService;
import com.hafizh.ems.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, UserMapper userMapper,
            JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
        this.jwtService = jwtService;
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
    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new AuthenticationException("Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new AuthenticationException("Invalid email or password");
        }

        String accessToken = jwtService.createAccessToken(user);
        String refreshToken = jwtService.createRefreshToken(user);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setAccessToken(accessToken);
        loginResponse.setRefreshToken(refreshToken);
        return loginResponse;
    }

    @Override
    public String refreshToken(RefreshTokenRequest request) {
        String email = jwtService.extractEmail(request.getRefreshToken());

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new AuthenticationException("Invalid refresh token"));

        if (!jwtService.validateRefreshToken(request.getRefreshToken(), user)) {
            throw new AuthenticationException("Invalid refresh token");
        }

        return jwtService.createAccessToken(user);
    }

}
