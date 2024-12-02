package com.backend.empowerpro.dto.medicalClaim;

import com.backend.empowerpro.auth.entity.Employee;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
@Data
@RequiredArgsConstructor

public class MedicalClaimDto {
    private Long id;
    private LocalDate date;
    private String forWhom;
    private String reason;
    private Long amount;
    private String fileUrl;

    private String name;
    private Long acc_no;
    private String bank;
    private String status;
    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private Employee createdBy;
    @CreationTimestamp
    private LocalDate createdAt;
    @UpdateTimestamp
    private LocalDate updatedAt;
}
