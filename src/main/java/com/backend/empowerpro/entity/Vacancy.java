package com.backend.empowerpro.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Entity
@Table(name = "vacancy")
public class Vacancy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vacancy_id" ,nullable = false   )
    private Long id;

    @Column(name = "jobTitle",nullable = false   )
    private String jobTitle;

    @Column(name = "employmentType" ,nullable = false  )
    private String employmentType;

    @Column(name = "jobDescription",nullable = false   )
    private String jobDescription;

    @Column(name = "requirements" ,nullable = false  )
    private String requirements;

    @Column(name = "responsibilities" ,nullable = false  )
    private String responsibilities;

    @Column(name = "deadLine" ,nullable = false  )
    private Date applicationDeadline;

    @Column(name = "contact_email" ,nullable = false  )
    private String contactEmail;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


}
