package com.backend.empowerpro.utils;

import com.backend.empowerpro.dto.events.EventCreationDto;
import com.backend.empowerpro.dto.events.EventDto;
import com.backend.empowerpro.dto.medicalClaim.MedicalClaimCreation;
import com.backend.empowerpro.dto.medicalClaim.MedicalClaimDto;
import com.backend.empowerpro.entity.Event;
import com.backend.empowerpro.entity.MedicalClaim;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClaimMapper {
    MedicalClaimCreation toMedicalClaimCreationDTO (MedicalClaim medicalClaim);
    MedicalClaim toMedicalClaim (MedicalClaimCreation medicalClaimCreation);
    MedicalClaimDto toMedicalClaimDto (MedicalClaim medicalClaim);

}
