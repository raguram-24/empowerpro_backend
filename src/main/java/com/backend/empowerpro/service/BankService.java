package com.backend.empowerpro.service;

import com.backend.empowerpro.dto.bank.BankCreationDto;
import com.backend.empowerpro.entity.BankDetails;

import java.util.List;

public interface BankService {
    public BankDetails createDetail(BankCreationDto bankCreationDto);
    public BankDetails updateDetail(BankCreationDto bankCreationDto, Long id);
    public List<BankDetails> getAllDetails();
    public BankDetails getOneDetail(Long id);
}
