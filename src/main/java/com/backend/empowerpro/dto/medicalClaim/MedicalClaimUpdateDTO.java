package com.backend.empowerpro.dto.medicalClaim;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicalClaimUpdateDTO {
    private Long id;
    private String status;
}
