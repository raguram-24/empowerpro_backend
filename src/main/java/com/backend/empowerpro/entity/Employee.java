package com.backend.empowerpro.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_id")
    private Long id;

    @Column(name = "first_name", length = 50, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 50, nullable = false)
    private String lastName;

    @Column(name = "address", length = 500, nullable = false)
    private String address;

    @Column(name = "email", length = 50, nullable = false)
    @Email
    private String email;

    @Column(name = "phone_number", nullable = false, unique = true)
    private int phoneNumber;

    @Column(name = "work_title", length = 50, nullable = false)
    private String workTitle;

    @Column(name = "role", length = 50, nullable = false)
    private String role;

    @Column(name = "username", length = 50, nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "summary", length = 50000)
    private String summary;

    @Column(name = "skills", length = 50000)
    private String skills;

    @Column(name = "experiences", length = 50000)
    private String experiences;


}


