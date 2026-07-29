package com.hafizh.ems.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DepartmentResponse {
    private Long id;
    private String name;
    private String description;
}
