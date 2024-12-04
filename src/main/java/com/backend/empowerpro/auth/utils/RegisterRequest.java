package com.backend.empowerpro.auth.utils;

import com.backend.empowerpro.auth.entity.EmployeeRole;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {

    @NotBlank(message = "firstName should not be Empty")
    private String firstName;
    @NotBlank(message = " lastName should not be Empty")
    private String lastName;
    @NotBlank(message = "address should not be Empty")
    private String address;
    @NotBlank(message = "email should not be Empty")
    private String email;
    @NotNull(message = "PhoneNumber should not be Empty")
    private Long phoneNumber;
    @NotBlank(message = "workTitle should not be Empty")
    private String workTitle;

    private EmployeeRole role;
    @NotBlank(message = "username should not be Empty")
    private String username;
    @NotNull(message = "password should not be Empty")
    private String password;

    private String profile;
    private String profilePath;





}
