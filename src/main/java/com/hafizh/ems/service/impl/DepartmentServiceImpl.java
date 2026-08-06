package com.hafizh.ems.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hafizh.ems.dto.request.department.CreateDepartmentRequest;
import com.hafizh.ems.dto.request.department.UpdateDepartmentRequest;
import com.hafizh.ems.dto.response.DepartmentResponse;
import com.hafizh.ems.entity.Department;
import com.hafizh.ems.exception.DuplicateResourceException;
import com.hafizh.ems.exception.ResourceNotFoundException;
import com.hafizh.ems.mapper.DepartmentMapper;
import com.hafizh.ems.repository.DepartmentRepository;
import com.hafizh.ems.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository, DepartmentMapper departmentMapper) {
        this.departmentRepository = departmentRepository;
        this.departmentMapper = departmentMapper;
    }

    @Override
    public List<DepartmentResponse> getAll() {
        return departmentRepository.findAll()
                .stream()
                .map(departmentMapper::toResponse)
                .toList();
    }

    @Override
    public DepartmentResponse getById(Long id) {
        return departmentRepository.findById(id)
                .map(departmentMapper::toResponse)
                .orElseThrow(() -> (new ResourceNotFoundException("Department with id: " + id + " does not exist")));
    }

    @Override
    public DepartmentResponse create(CreateDepartmentRequest request) {
        if (departmentRepository.existsByName(request.getName())) {
            throw new DuplicateResourceException("Department with name '" + request.getName() + "' already exists");
        }

        Department department = departmentMapper.toCreateEntity(request);

        Department savedDepartment = departmentRepository.save(department);

        return departmentMapper.toResponse(savedDepartment);
    }

    @Override
    public DepartmentResponse update(Long id, UpdateDepartmentRequest request) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Department with id '" + id + "' does not exist"));

        if (departmentRepository.existsByNameAndIdNot(request.getName(), id)) {
            throw new DuplicateResourceException("Department with name '" + request.getName() + "' already exists");
        }

        departmentMapper.updateEntity(department, request);

        Department savedDepartment = departmentRepository.save(department);

        return departmentMapper.toResponse(savedDepartment);
    }

    @Override
    public void delete(Long id) {
        departmentRepository.findById(id)
                .orElseThrow(() -> (new ResourceNotFoundException("Department with id: " + id + " does not exist")));

        departmentRepository.deleteById(id);
    }
}
