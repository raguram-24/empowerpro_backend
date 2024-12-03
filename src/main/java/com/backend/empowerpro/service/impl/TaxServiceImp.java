package com.backend.empowerpro.service.impl;

import com.backend.empowerpro.dto.events.EventDto;
import com.backend.empowerpro.dto.tax.TaxCreationDto;
import com.backend.empowerpro.dto.tax.TaxDto;
import com.backend.empowerpro.entity.Event;
import com.backend.empowerpro.entity.MedicalClaim;
import com.backend.empowerpro.entity.Tax;
import com.backend.empowerpro.exception.CustomAuthenticationException;
import com.backend.empowerpro.exception.MedicalClaimNotFound;
import com.backend.empowerpro.repository.TaxRepo;
import com.backend.empowerpro.service.TaxService;
import com.backend.empowerpro.utils.LoggedInEmployee;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaxServiceImp implements TaxService {
    private final TaxRepo taxRepo;
    private static final Logger logger = LoggerFactory.getLogger(TaxServiceImp.class);

    @Override
    public Tax createTax(TaxCreationDto taxCreationDto) {
        try{
            Tax newTax = new Tax(
                    taxCreationDto.getCurrencyType(),
                    taxCreationDto.getTaxRate(),
                    taxCreationDto.getEpf(),
                    taxCreationDto.getMedicalAllowance(),
                    taxCreationDto.getLeaveDeduction(),
                    taxCreationDto.getOtherAllowance()
            );
            taxRepo.save(newTax);
            return newTax;
        }catch (Exception e){
            logger.error("Error Has been Occurred {}",e.getMessage());
            throw new RuntimeException("An unexpected error occurred while Creating Tax", e);
        }
    }

    @Override
    public Tax updateTax(TaxCreationDto taxCreationDto, Long id) {
        return null;
    }

    @Override
    public List<Tax> getAllTax() {
        try {
            List<Tax> allTax = taxRepo.findAll();
            logger.info("All MedicalClaims found");
            return allTax;
        }catch (Exception e){
            logger.error("An unexpected error occurred while fetching all tax: {}", e.getMessage(), e);
            throw new RuntimeException("An unexpected error occurred while fetching tax", e);

        }
    }

    @Override
    public Tax getOneTax(Long id) {
        try {
            var tax = taxRepo.findById(id);
            if (tax.isPresent()){
                Tax tax1 = tax.get();
                logger.info("Retrieved tax : {}", id);
                return tax1;
            }else {
                throw new MedicalClaimNotFound("Not found");
            }
        }catch (Exception e){
            logger.error("An unexpected error occurred while fetching tax: {}", e.getMessage(), e);
            throw new RuntimeException("An unexpected error occurred while fetching tax", e);
        }
    }

    @Override
    public Tax getLatestTax() {
        try{
            Tax latestTax = taxRepo.findLatestById();
            return latestTax;
        }catch (Exception e){
            logger.error("An unexpected error occurred while fetching tax: {}", e.getMessage(), e);
            throw new RuntimeException("An unexpected error occurred while fetching tax", e);
        }
    }
}
