package com.backend.empowerpro.service;

import com.backend.empowerpro.dto.events.EventCreationDto;
import com.backend.empowerpro.dto.events.EventDto;
import com.backend.empowerpro.dto.events.EventUpdateDto;
import com.backend.empowerpro.dto.tax.TaxCreationDto;
import com.backend.empowerpro.dto.tax.TaxDto;
import com.backend.empowerpro.entity.Tax;

import java.util.List;

public interface TaxService {
    public Tax createTax(TaxCreationDto taxCreationDto);
    public Tax updateTax(TaxCreationDto taxCreationDto, Long id);
    public List<Tax> getAllTax();
    public Tax getOneTax(Long id);
}
