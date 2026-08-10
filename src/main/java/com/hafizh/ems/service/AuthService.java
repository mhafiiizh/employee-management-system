package com.hafizh.ems.service;

import java.util.List;

import com.hafizh.ems.dto.request.user.LoginRequest;
import com.hafizh.ems.dto.request.user.RegisterRequest;
import com.hafizh.ems.dto.response.UserResponse;

public interface AuthService {
    UserResponse register(RegisterRequest request);

    String login(LoginRequest request);

}
