package com.backend.empowerpro.dto.employee;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmployeeUpdateDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private int phoneNumber;
    private String role;
    private String workTitle;
    private String userName;
    private String password;
    private String summary;
    private String skills;
    private String experiences;

}
