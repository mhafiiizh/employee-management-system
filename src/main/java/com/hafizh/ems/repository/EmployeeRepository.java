package com.hafizh.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hafizh.ems.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    boolean existsByEmail(String email);

    boolean existsByEmailAndIdNot(String email, Long id);

}
