package com.backend.empowerpro.dto.vacancy;

import com.backend.empowerpro.entity.Employee;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VacancyDto {
    private Long id;
    private String jobTitle;
    private String employmentType;
    private String jobDescription;
    private String requirements;
    private String responsibilities;
    private Date applicationDeadline;
    private String contactEmail;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


}
