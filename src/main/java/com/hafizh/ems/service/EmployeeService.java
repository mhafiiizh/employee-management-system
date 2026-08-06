package com.hafizh.ems.service;

import java.util.List;

import com.hafizh.ems.dto.request.employee.CreateEmployeeRequest;
import com.hafizh.ems.dto.request.employee.UpdateEmployeeRequest;
import com.hafizh.ems.dto.response.EmployeeResponse;

public interface EmployeeService {
    List<EmployeeResponse> getAll();

    EmployeeResponse getById(Long id);

    EmployeeResponse create(CreateEmployeeRequest request);

    EmployeeResponse update(Long id, UpdateEmployeeRequest request);

    void delete(Long id);
}
