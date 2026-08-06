package com.hafizh.ems.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hafizh.ems.dto.request.department.CreateDepartmentRequest;
import com.hafizh.ems.dto.request.department.UpdateDepartmentRequest;
import com.hafizh.ems.dto.response.ApiResponse;
import com.hafizh.ems.dto.response.DepartmentResponse;
import com.hafizh.ems.service.DepartmentService;

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
@RequestMapping("/api/v1/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<DepartmentResponse>>> getAll() {
        List<DepartmentResponse> departments = departmentService.getAll();

        return ResponseEntity.ok(
                ApiResponse.success("Departments retrieved successfully", departments));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<DepartmentResponse>> create(@Valid @RequestBody CreateDepartmentRequest request) {
        DepartmentResponse department = departmentService.create(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Department created successfully", department));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<DepartmentResponse>> getById(@PathVariable Long id) {
        DepartmentResponse department = departmentService.getById(id);

        return ResponseEntity.ok(ApiResponse.success("Department retrieved succesfully", department));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<DepartmentResponse>> update(@PathVariable Long id,
            @RequestBody UpdateDepartmentRequest request) {
        DepartmentResponse department = departmentService.update(id, request);

        return ResponseEntity.ok(ApiResponse.success("Department updated successfully", department));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        departmentService.delete(id);

        return ResponseEntity.ok(ApiResponse.success("Department deleted successfully", null));
    }

}
