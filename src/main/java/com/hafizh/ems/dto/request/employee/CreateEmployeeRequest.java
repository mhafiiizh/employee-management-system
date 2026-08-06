package com.hafizh.ems.dto.request.employee;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateEmployeeRequest {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Email format must be valid")
    private String email;

    @NotBlank(message = "Position is required")
    private String position;

    @NotNull(message = "Department ID is required")
    private Long departmentId;
}
