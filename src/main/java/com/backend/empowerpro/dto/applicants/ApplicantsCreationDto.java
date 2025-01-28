package com.backend.empowerpro.dto.applicants;

import com.backend.empowerpro.dto.vacancy.VacancyDto;
import com.backend.empowerpro.entity.Vacancy;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ApplicantsCreationDto {
    private Long id;
    private Long vacancyId;
    private String firstName;
    private String lastName;
    private String city;
    private String country;
    private String address;
    private String email;
    private int phoneNumber;
    private String resume;
    private VacancyDto vacancy;
}
