package com.backend.empowerpro.service.impl;

import com.backend.empowerpro.auth.repository.EmployeeRepository;
import com.backend.empowerpro.dto.events.EventDto;
import com.backend.empowerpro.dto.medicalClaim.MedicalClaimCreation;
import com.backend.empowerpro.dto.medicalClaim.MedicalClaimDto;
import com.backend.empowerpro.dto.medicalClaim.MedicalClaimUpdateDTO;
import com.backend.empowerpro.entity.Event;
import com.backend.empowerpro.entity.MedicalClaim;
import com.backend.empowerpro.repository.MedicalClaimRepo;
import com.backend.empowerpro.service.MedicalClaimService;
import com.backend.empowerpro.utils.ClaimMapper;
import com.backend.empowerpro.utils.LoggedInEmployee;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class MedicalClaimServiceImp implements MedicalClaimService {
    private final MedicalClaimRepo medicalClaimRepo;
    private final ClaimMapper claimMapper;
    private final EmployeeRepository employeeRepository;
    private static final Logger logger = LoggerFactory.getLogger(MedicalClaimServiceImp.class);
    @Override
    public MedicalClaim createClaim(MedicalClaimCreation medicalClaimCreation) {
        try{
            String username = LoggedInEmployee.getLoggedInUsername();
            var employee = employeeRepository
                    .findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("Employee not found!"));

            MedicalClaim newClaim = claimMapper.toMedicalClaim(medicalClaimCreation);
            newClaim.setCreatedBy(employee);
            medicalClaimRepo.save(newClaim);
            logger.info("New Event Created with" + newClaim);
            return newClaim;
        }catch (Exception e){
            logger.error("Error Has been Occurred {}",e.getMessage());
            throw new RuntimeException("An unexpected error occurred while fetching Accounts", e);
        }
    }

    @Override
    public MedicalClaimDto updateClaim(MedicalClaimUpdateDTO medicalClaimUpdateDTO, Long id) {
        return null;
    }

    @Override
    public List<MedicalClaimDto> getAllClaim() {
        return List.of();
    }

    @Override
    public MedicalClaimDto getOneClaim(Long id) {
        return null;
    }
}
