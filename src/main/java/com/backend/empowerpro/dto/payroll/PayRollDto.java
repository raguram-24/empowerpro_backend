package com.backend.empowerpro.dto.payroll;

import com.backend.empowerpro.auth.entity.Employee;
import com.backend.empowerpro.entity.BankDetails;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@RequiredArgsConstructor
public class PayRollDto {
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


}
