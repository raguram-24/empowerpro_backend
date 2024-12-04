package com.backend.empowerpro.dto.payroll;

import com.backend.empowerpro.auth.entity.Employee;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Data
@RequiredArgsConstructor
public class PayRollViewDto {
     private Long id;
     private String name;
     private String postion;
     private BigDecimal grossPay;
     private BigDecimal Tax;
     private BigDecimal totalSalary;
     private String status;

}
