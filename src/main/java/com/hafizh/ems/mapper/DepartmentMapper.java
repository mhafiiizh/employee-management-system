package com.hafizh.ems.mapper;

import org.springframework.stereotype.Component;

import com.hafizh.ems.dto.request.department.CreateDepartmentRequest;
import com.hafizh.ems.dto.request.department.UpdateDepartmentRequest;
import com.hafizh.ems.dto.response.DepartmentResponse;
import com.hafizh.ems.entity.Department;

@Component
public class DepartmentMapper {
    public DepartmentResponse toResponse(Department department) {
        if (department == null) {
            return null;
        }

        DepartmentResponse response = new DepartmentResponse();
        response.setId(department.getId());
        response.setName(department.getName());
        response.setDescription(department.getDescription());
        return response;
    }

    public Department toCreateEntity(CreateDepartmentRequest request) {
        if (request == null) {
            return null;
        }

        Department department = new Department();
        department.setName(request.getName());
        department.setDescription(request.getDescription());

        return department;
    }

    public Department updateEntity(Department department, UpdateDepartmentRequest request) {
        if (request == null) {
            return null;
        }

        department.setName(request.getName());
        department.setDescription(request.getDescription());

        return department;
    }
}
