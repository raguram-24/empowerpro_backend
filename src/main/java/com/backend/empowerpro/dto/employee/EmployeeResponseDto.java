package com.backend.empowerpro.dto.employee;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmployeeResponseDto {
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private int phoneNumber;
    private String workTitle;
    private String role;
    private String userName;
    private String token;
}
