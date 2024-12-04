package com.backend.empowerpro.dto.tax;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class TaxDto {
    private Long id;
    private String currencyType;
    private float taxRate;
    private float epf;
    private float medicalAllowance;
    private float leaveDeduction;
    private float otherAllowance;
}
