package com.backend.empowerpro.dto.medicalClaim;

import com.backend.empowerpro.auth.entity.Employee;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
@Data
public class MedicalClaimCreation {

    private LocalDate date;
    private String forWhom;
    private String reason;
    private Long amount;
    private String fileUrl;
    private String status;
    private String name;
    private Long acc_no;
    private String bank;

}
