package com.backend.empowerpro.dto.employee;

import com.backend.empowerpro.auth.entity.EmployeeRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeCreationDto {
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private Long phoneNumber;
    private EmployeeRole role;
    private String workTitle;
    private String username;
    private String password;
    private String summary;
    private String skills;
    private String experiences;
    private String profile;
}
