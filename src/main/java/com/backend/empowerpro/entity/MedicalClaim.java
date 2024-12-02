package com.backend.empowerpro.entity;


import com.backend.empowerpro.auth.entity.Employee;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "claims")
public class MedicalClaim {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "claim_id" ,nullable = false)
    private Long id;
    private LocalDate date;
    private String forWhom;
    private String reason;
    private Long amount;
    private String fileUrl;
    private String name;
    private Long acc_no;
    private String bank;
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private Employee createdBy;
    @CreationTimestamp
    private LocalDate createdAt;
    @UpdateTimestamp
    private LocalDate updatedAt;
}
