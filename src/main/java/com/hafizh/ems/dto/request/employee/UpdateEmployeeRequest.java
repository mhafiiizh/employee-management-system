package com.hafizh.ems.dto.request.employee;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateEmployeeRequest {
    private String name;

    @Email(message = "Email format must be valid")
    private String email;

    private String position;
}
