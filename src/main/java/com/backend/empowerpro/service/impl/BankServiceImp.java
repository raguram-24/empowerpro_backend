package com.backend.empowerpro.service.impl;

import com.backend.empowerpro.auth.repository.EmployeeRepository;
import com.backend.empowerpro.dto.bank.BankCreationDto;
import com.backend.empowerpro.entity.BankDetails;
import com.backend.empowerpro.repository.BankRepo;
import com.backend.empowerpro.service.BankService;
import com.backend.empowerpro.utils.LoggedInEmployee;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

@AllArgsConstructor
public class BankServiceImp implements BankService {
    private final BankRepo bankRepo;
    private final EmployeeRepository employeeRepository;
    private static final Logger logger = LoggerFactory.getLogger(BankServiceImp.class);
    @Override
    public BankDetails createDetail(BankCreationDto bankCreationDto) {
        logger.info("Function Called");
        try{
            var employee = employeeRepository
                    .findByUsername(bankCreationDto.getUsername())
                    .orElseThrow(() -> new UsernameNotFoundException("Employee not found!"));
            logger.info("Employee Found");
            BankDetails newDetails = new BankDetails(
                    employee,
                    bankCreationDto.getBankName(),
                    bankCreationDto.getAcc_no(),
                    bankCreationDto.getBranch()
            );
            logger.info("Details Created");
            bankRepo.save(newDetails);
            return newDetails;




        }catch (Exception e){
            logger.error("Error Has been Occurred {}",e.getMessage());
            throw new RuntimeException("An unexpected error occurred while Creating Bankdetails", e);
        }
    }

    @Override
    public BankDetails updateDetail(BankCreationDto bankCreationDto, Long id) {
        return null;
    }

    @Override
    public List<BankDetails> getAllDetails() {
        return List.of();
    }

    @Override
    public BankDetails getOneDetail(Long id) {
        return null;
    }
}
