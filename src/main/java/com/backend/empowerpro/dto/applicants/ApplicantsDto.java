package com.backend.empowerpro.dto.applicants;

import com.backend.empowerpro.dto.vacancy.VacancyDto;
import com.backend.empowerpro.entity.Vacancy;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ApplicantsDto {
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
    private LocalDateTime submittedAt;
    private VacancyDto vacancy;

}
