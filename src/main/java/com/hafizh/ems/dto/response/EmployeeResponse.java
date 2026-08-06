package com.hafizh.ems.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeResponse {
    private Long id;
    private String name;
    private String email;
    private String position;
    private DepartmentResponse department;
}
