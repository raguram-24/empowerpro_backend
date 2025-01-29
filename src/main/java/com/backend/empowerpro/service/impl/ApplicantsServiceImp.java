package com.backend.empowerpro.service.impl;

import com.backend.empowerpro.auth.entity.Employee;
import com.backend.empowerpro.dto.applicants.ApplicantsCreationDto;
import com.backend.empowerpro.dto.applicants.ApplicantsDto;
import com.backend.empowerpro.dto.complaint.ComplaintCreationDto;
import com.backend.empowerpro.dto.complaint.ComplaintDto;
import com.backend.empowerpro.entity.Applicants;
import com.backend.empowerpro.entity.Complaint;
import com.backend.empowerpro.entity.Vacancy;
import com.backend.empowerpro.exception.ResourceNotFoundException;
import com.backend.empowerpro.repository.ApplicantsRepo;
import com.backend.empowerpro.repository.VacancyRepo;
import com.backend.empowerpro.service.ApplicantsService;
import com.backend.empowerpro.utils.ApplicantsMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApplicantsServiceImp implements ApplicantsService {
    private final ApplicantsRepo applicantsRepo;
    private final VacancyRepo vacancyRepo;
    //    private final FileDownloadUtil fileDownloadUtil;
//    private final FileUploadUtil fileUploadUtil;
    private final ApplicantsMapper applicantsMapper;
    private static final Logger logger = LoggerFactory.getLogger(Applicants.class);
    @Override
    public List<ApplicantsDto> getAllApplicants() {
        try{
            //Fetching All Accounts Details from DB
            List<Applicants> allApplicants = applicantsRepo.findAll();
//            logger.info("All Applicants fetched Successfully");
            return allApplicants.stream()
                    .map(applicantsMapper::toApplicantsDto)
                    .collect(Collectors.toList());

        }catch(Exception e){
            logger.error("Error Has been Occurred {}",e.getMessage());
            throw new RuntimeException("An unexpected error occurred while fetching Applicants", e);
        }
    }

    @Override
    public ApplicantsDto createApplicants(ApplicantsCreationDto applicantsCreationDto) {
        try {
            // Validate Vacancy
            Vacancy vacancy = vacancyRepo.findById(applicantsCreationDto.getVacancyId())
                    .orElseThrow(() -> new ResourceNotFoundException("Vacancy not found with ID: " + applicantsCreationDto.getVacancyId()));

            // Map DTO to Entity
            Applicants applicant = new Applicants();
            applicant.setFirstName(applicantsCreationDto.getFirstName());
            applicant.setLastName(applicantsCreationDto.getLastName());
            applicant.setAddress(applicantsCreationDto.getAddress());
            applicant.setEmail(applicantsCreationDto.getEmail());
            applicant.setPhoneNumber(applicantsCreationDto.getPhoneNumber());
            applicant.setResume(applicantsCreationDto.getResume());
            applicant.setVacancy(vacancy);

            // Save the applicant entity
            Applicants savedApplicant = applicantsRepo.save(applicant);

            return applicantsMapper.toApplicantsDto(savedApplicant);

        } catch (Exception e) {
            throw new RuntimeException("Error while creating applicant: " + e.getMessage(), e);
        }
    }

    @Override
    public ApplicantsDto getOneApplicant(Long id) {
        return null;
    }

    @Override
    public ApplicantsDto updateApplicant(Long id, ApplicantsCreationDto applicantsCreationDto) {
        return null;
    }

    @Override
    public String deleteVacancy(Long id) {
        return "";
    }
}
