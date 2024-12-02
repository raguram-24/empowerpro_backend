package com.backend.empowerpro.dto.employee;

import com.backend.empowerpro.auth.entity.EmployeeRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
    private Long employeeId;
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private String username;
    private String workTitle;
    private EmployeeRole role;
    private String summary;
    private String skills;
    private String experiences;
    private String profilePath;
}
