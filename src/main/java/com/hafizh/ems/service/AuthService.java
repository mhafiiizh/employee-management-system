package com.hafizh.ems.service;

import com.hafizh.ems.dto.request.auth.LoginRequest;
import com.hafizh.ems.dto.request.auth.RefreshTokenRequest;
import com.hafizh.ems.dto.request.auth.RegisterRequest;
import com.hafizh.ems.dto.response.LoginResponse;
import com.hafizh.ems.dto.response.UserResponse;

public interface AuthService {
    UserResponse register(RegisterRequest request);

    LoginResponse login(LoginRequest request);

    String refreshToken(RefreshTokenRequest request);

}
