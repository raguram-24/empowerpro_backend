package com.backend.empowerpro.dto.employee;

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
    private String phoneNumber;
    private String workTitle;
    private String role;
    private String username;
    private String experiences;
    private String skills;
    private String summary;
}
