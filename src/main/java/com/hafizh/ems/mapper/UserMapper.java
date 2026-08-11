package com.hafizh.ems.mapper;

import org.springframework.stereotype.Component;

import com.hafizh.ems.dto.request.auth.RegisterRequest;
import com.hafizh.ems.dto.response.UserResponse;
import com.hafizh.ems.entity.Role;
import com.hafizh.ems.entity.User;

@Component
public class UserMapper {
    public UserResponse toResponse(User user) {
        if (user == null) {
            return null;
        }

        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setEmail(user.getEmail());
        response.setRole(user.getRole().name());
        return response;
    }

    public User toCreateEntity(RegisterRequest request, Role role, String encodedPassword) {
        if (request == null) {
            return null;
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(encodedPassword);
        user.setRole(role);
        return user;
    }

    public User updateEntity(User user, UserResponse response) {
        if (response == null) {
            return null;
        }

        user.setEmail(response.getEmail());
        user.setRole(Role.valueOf(response.getRole()));
        return user;
    }
}
