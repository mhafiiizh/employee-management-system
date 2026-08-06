package com.hafizh.ems.mapper;

import org.springframework.stereotype.Component;

import com.hafizh.ems.dto.request.employee.CreateEmployeeRequest;
import com.hafizh.ems.dto.request.employee.UpdateEmployeeRequest;
import com.hafizh.ems.dto.response.EmployeeResponse;
import com.hafizh.ems.entity.Department;
import com.hafizh.ems.entity.Employee;

@Component
public class EmployeeMapper {
    private final DepartmentMapper departmentMapper;

    public EmployeeMapper(DepartmentMapper departmentMapper) {
        this.departmentMapper = departmentMapper;
    }

    public EmployeeResponse toResponse(Employee employee) {
        if (employee == null) {
            return null;
        }

        EmployeeResponse response = new EmployeeResponse();
        response.setId(employee.getId());
        response.setName(employee.getName());
        response.setEmail(employee.getEmail());
        response.setPosition(employee.getPosition());
        response.setDepartment(departmentMapper.toResponse(employee.getDepartment()));
        return response;
    }

    public Employee toCreateEntity(CreateEmployeeRequest request, Department department) {
        if (request == null) {
            return null;
        }

        Employee employee = new Employee();
        employee.setName(request.getName());
        employee.setEmail(request.getEmail());
        employee.setPosition(request.getPosition());
        employee.setDepartment(department);

        return employee;
    }

    public Employee updateEntity(Employee employee, UpdateEmployeeRequest request, Department department) {
        if (request == null) {
            return null;
        }

        employee.setName(request.getName());
        employee.setEmail(request.getEmail());
        employee.setPosition(request.getPosition());
        employee.setDepartment(department);

        return employee;
    }
}
