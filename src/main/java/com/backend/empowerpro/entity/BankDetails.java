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
@Table(name = "bank_details")
public class BankDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bank_id" ,nullable = false   )
    private Long id;
    @ManyToOne
    @JoinColumn(name = "employee", nullable = false)
    private Employee employee;
    private String bankName;
    private String acc_no;
    private String branch;
    @CreationTimestamp
    private LocalDate createdAt;
    @UpdateTimestamp
    private LocalDate updatedAt;

    public BankDetails(Employee employee, String bankName, String accNo, String branch) {
        this.employee = employee;
        this.bankName = bankName;
        this.acc_no = accNo;
        this.branch = branch;
    }
}
