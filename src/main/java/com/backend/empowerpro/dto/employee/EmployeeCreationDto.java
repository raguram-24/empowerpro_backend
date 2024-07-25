package com.backend.empowerpro.dto.employee;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmployeeCreationDto {
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private int phoneNumber;
    private String workTitle;
    private String username;
    private String password;
    private String role;

}
