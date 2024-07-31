package com.backend.empowerpro.service;

import com.backend.empowerpro.dto.LoginDto;
import com.backend.empowerpro.dto.applicants.ApplicantsCreationDto;
import com.backend.empowerpro.dto.applicants.ApplicantsDto;
import com.backend.empowerpro.dto.employee.EmployeeCreationDto;
import com.backend.empowerpro.dto.employee.EmployeeDto;
import com.backend.empowerpro.dto.employee.EmployeeResponseDto;
import com.backend.empowerpro.dto.employee.EmployeeUpdateDto;
import com.backend.empowerpro.dto.vacancy.VacancyCreationDto;
import com.backend.empowerpro.dto.vacancy.VacancyDto;
import com.backend.empowerpro.entity.Applicants;
import com.backend.empowerpro.entity.Employee;

import java.util.List;

public interface ApplicantsService {
    public List<ApplicantsDto> getAllApplicants();
    public String createApplicants(ApplicantsCreationDto applicantsCreationDto);
    public ApplicantsDto getOneApplicant(Long id);
    public ApplicantsDto updateApplicant(Long id,ApplicantsCreationDto applicantsCreationDto);
    public String deleteVacancy(Long id);
}
