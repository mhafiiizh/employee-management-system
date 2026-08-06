package com.hafizh.ems.service;

import java.util.List;

import com.hafizh.ems.dto.request.department.CreateDepartmentRequest;
import com.hafizh.ems.dto.request.department.UpdateDepartmentRequest;
import com.hafizh.ems.dto.response.DepartmentResponse;

public interface DepartmentService {
    List<DepartmentResponse> getAll();

    DepartmentResponse getById(Long id);

    DepartmentResponse create(CreateDepartmentRequest request);

    DepartmentResponse update(Long id, UpdateDepartmentRequest request);

    void delete(Long id);
}
