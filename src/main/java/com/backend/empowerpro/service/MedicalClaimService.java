package com.backend.empowerpro.service;


import com.backend.empowerpro.dto.medicalClaim.MedicalClaimCreation;
import com.backend.empowerpro.dto.medicalClaim.MedicalClaimDto;
import com.backend.empowerpro.dto.medicalClaim.MedicalClaimUpdateDTO;
import com.backend.empowerpro.entity.MedicalClaim;

import java.util.List;

public interface MedicalClaimService {
    public MedicalClaim createClaim(MedicalClaimCreation medicalClaimCreation);
    public MedicalClaimDto updateClaim(MedicalClaimUpdateDTO medicalClaimUpdateDTO, Long id);
    public List<MedicalClaimDto> getAllClaim();
    public MedicalClaimDto getOneClaim(Long id);
}
