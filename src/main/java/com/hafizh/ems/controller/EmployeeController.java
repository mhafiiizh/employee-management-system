package com.hafizh.ems.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hafizh.ems.dto.request.employee.CreateEmployeeRequest;
import com.hafizh.ems.dto.request.employee.UpdateEmployeeRequest;
import com.hafizh.ems.dto.response.ApiResponse;
import com.hafizh.ems.dto.response.EmployeeResponse;
import com.hafizh.ems.service.EmployeeService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<EmployeeResponse>>> getAll() {
        List<EmployeeResponse> employees = employeeService.getAll();

        return ResponseEntity.ok(
                ApiResponse.success("Employees retrieved successfully", employees));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<EmployeeResponse>> create(@Valid @RequestBody CreateEmployeeRequest request) {
        EmployeeResponse employee = employeeService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Employee created successfully", employee));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<EmployeeResponse>> getById(@PathVariable Long id) {
        EmployeeResponse employee = employeeService.getById(id);
        return ResponseEntity.ok(ApiResponse.success("Employee retrieved successfully", employee));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<EmployeeResponse>> update(@PathVariable Long id,
            @Valid @RequestBody UpdateEmployeeRequest request) {
        EmployeeResponse employee = employeeService.update(id, request);
        return ResponseEntity.ok(ApiResponse.success("Employee updated successfully", employee));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        employeeService.delete(id);
        return ResponseEntity.ok(ApiResponse.success("Employee deleted successfully", null));
    }

}
