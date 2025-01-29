package com.backend.empowerpro.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Entity
@Table(name = "applicants")
public class Applicants {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "applicant_id" ,nullable = false   )
    private Long id;

    @Column(name = "first_name", length = 50, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 50, nullable = false)
    private String lastName;

    @Column(name = "city", length = 50)
    private String city;

    @Column(name = "country", length = 50)
    private String country;

    @Column(name = "address", length = 500, nullable = false)
    private String address;

    @Column(name = "email", length = 50, nullable = false, unique = true)
    @Email
    private String email;

    @Column(name = "phone_number", nullable = false, unique = true)
    private int phoneNumber;

    @Column(name = "resume",nullable = false)
    private String resume;

    @CreationTimestamp
    @Column(name = "submitted_at", updatable = false)
    private LocalDateTime submittedAt;

    @ManyToOne
    @JoinColumn(name = "vacancy_id", referencedColumnName = "vacancy_id")
    private Vacancy vacancy;



}
