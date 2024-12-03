package com.backend.empowerpro.entity;

import com.backend.empowerpro.auth.entity.Employee;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "payroll")
@AllArgsConstructor
public class PayRoll {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private Long employeeId;
    private String firstName;
    private String address;
    private String jobTitle;
    private BigDecimal salary;
    private BigDecimal medicalAllowance;
    private BigDecimal otherAllowance;
    private BigDecimal incomeAfterTax;
    private BigDecimal incomeAfterEpf;
    private BigDecimal totalAmount;

    private String bankName;
    private String acc_no;
    private String branch;
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    public PayRoll(Long employeeId, String firstName, String address, String jobTitle, BigDecimal salary, BigDecimal medicalAllowance, BigDecimal otherAllowance, BigDecimal incomeAfterTax, BigDecimal incomeAfterEpf, BigDecimal totalAmount, String bankName, String acc_no, String branch, Status status) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.address = address;
        this.jobTitle = jobTitle;
        this.salary = salary;
        this.medicalAllowance = medicalAllowance;
        this.otherAllowance = otherAllowance;
        this.incomeAfterTax = incomeAfterTax;
        this.incomeAfterEpf = incomeAfterEpf;
        this.totalAmount = totalAmount;
        this.bankName = bankName;
        this.acc_no = acc_no;
        this.branch = branch;
        this.status = status;
    }
}
