package com.backend.empowerpro.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data

@Entity
@Table(name = "tax")
@AllArgsConstructor
public class Tax {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "leave_id")
    private Long id;
    private String currencyType;
    private float taxRate;
    private float epf;
    private float medicalAllowance;
    private float leaveDeduction;
    private float otherAllowance;

    public Tax(String currencyType, float taxRate, float epf, float medicalAllowance, float leaveDeduction, float otherAllowance) {
        this.currencyType = currencyType;
        this.taxRate = taxRate;
        this.epf = epf;
        this.medicalAllowance = medicalAllowance;
        this.leaveDeduction = leaveDeduction;
        this.otherAllowance = otherAllowance;
    }
}
