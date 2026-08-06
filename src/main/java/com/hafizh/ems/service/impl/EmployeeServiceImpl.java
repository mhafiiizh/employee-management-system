package com.hafizh.ems.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hafizh.ems.dto.request.employee.CreateEmployeeRequest;
import com.hafizh.ems.dto.request.employee.UpdateEmployeeRequest;
import com.hafizh.ems.dto.response.EmployeeResponse;
import com.hafizh.ems.entity.Department;
import com.hafizh.ems.entity.Employee;
import com.hafizh.ems.exception.DuplicateResourceException;
import com.hafizh.ems.exception.ResourceNotFoundException;
import com.hafizh.ems.mapper.EmployeeMapper;
import com.hafizh.ems.repository.DepartmentRepository;
import com.hafizh.ems.repository.EmployeeRepository;
import com.hafizh.ems.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;
    private DepartmentRepository departmentRepository;
    private EmployeeMapper employeeMapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository,
            EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.employeeMapper = employeeMapper;
    }

    @Override
    public List<EmployeeResponse> getAll() {
        return employeeRepository.findAll()
                .stream()
                .map(employeeMapper::toResponse)
                .toList();
    }

    @Override
    public EmployeeResponse getById(Long id) {
        return employeeRepository.findById(id)
                .map(employeeMapper::toResponse)
                .orElseThrow(() -> (new ResourceNotFoundException("Employee with id: " + id + " does not exist")));
    }

    @Override
    public EmployeeResponse create(CreateEmployeeRequest request) {
        if (employeeRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException("Employee with email '" + request.getEmail() + "' already exists");
        }

        Department department = departmentRepository
                .findById(request.getDepartmentId())
                .orElseThrow(() -> (new ResourceNotFoundException(
                        "Department with id: " + request.getDepartmentId() + " does not exist")));

        Employee employee = employeeMapper.toCreateEntity(request, department);
        Employee savedEmployee = employeeRepository.save(employee);

        return employeeMapper.toResponse(savedEmployee);
    }

    @Override
    public EmployeeResponse update(Long id, UpdateEmployeeRequest request) {
        if (employeeRepository.existsByEmailAndIdNot(request.getEmail(), id)) {
            throw new DuplicateResourceException("Employee with email '" + request.getEmail() + "' already exists");
        }

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> (new ResourceNotFoundException(
                        "Employee with id: " + id + " does not exist")));

        Department department = departmentRepository
                .findById(employee.getDepartment().getId())
                .orElseThrow(() -> (new ResourceNotFoundException(
                        "Department with id: " + employee.getDepartment().getId() + " does not exist")));

        Employee employeeReq = employeeMapper.updateEntity(employee, request, department);
        Employee employeeUpdated = employeeRepository.save(employeeReq);

        return employeeMapper.toResponse(employeeUpdated);
    }

    @Override
    public void delete(Long id) {
        employeeRepository.findById(id)
                .orElseThrow(() -> (new ResourceNotFoundException("Employee with id: " + id + " does not exist")));

        employeeRepository.deleteById(id);
    }

}
