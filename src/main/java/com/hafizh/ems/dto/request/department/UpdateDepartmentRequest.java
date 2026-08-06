package com.hafizh.ems.dto.request.department;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateDepartmentRequest {
    @Size(max = 100, message = "Department name must not exceed 100 characters")
    private String name;

    @Size(max = 500, message = "Department description must not exceed 500 characters")
    private String description;
}
