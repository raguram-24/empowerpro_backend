package com.backend.empowerpro.dto.applicants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ApplicantsCreationDto {
    private Long id;
    private String firstName;
    private String lastName;
    private Integer nic;
    private String address;
    private String email;
    private int phoneNumber;
    private String university;
    private String experienceTitle;
    private String companyName;
    private Date workedFrom;
    private Date workedTo;
    private String resumePath;
}