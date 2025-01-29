package com.backend.empowerpro.controller;


import com.backend.empowerpro.dto.Inquiry.InquiryCreationDto;
import com.backend.empowerpro.dto.Inquiry.InquiryDto;

import com.backend.empowerpro.dto.applicants.ApplicantsCreationDto;
import com.backend.empowerpro.dto.applicants.ApplicantsDto;
import com.backend.empowerpro.service.ApplicantsService;
import com.backend.empowerpro.service.EmployeeService;
import com.backend.empowerpro.service.InquiryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
public class UserController {
    private final InquiryService inquiryService;
    private final ApplicantsService applicantsService;
    private final String UPLOAD_DIR_RESUMES = "D:\\3rd year\\New folder\\empowerpro_backend\\uploads\\resumes";

    @PostMapping("/inquiry-create")
    public ResponseEntity<String> creation(@RequestBody InquiryCreationDto inquiryCreationDto){
        return ResponseEntity.ok(inquiryService.createInquiry(inquiryCreationDto));
    }
    @GetMapping("/inquiry-get-all")
    public ResponseEntity<List<InquiryDto>> getAllInquiries() {
        return ResponseEntity.ok(inquiryService.getAllInquiries());
    }

    @GetMapping("/inquiry-get-one/{id}")
    public ResponseEntity<InquiryDto> getOneVacancy(@PathVariable Long id) {
        return ResponseEntity.ok(inquiryService.getOneInquiry(id));
    }

    @PutMapping("/inquiry-update/{id}")
    public ResponseEntity<InquiryDto> updateInquiry(@PathVariable Long id, @RequestBody InquiryCreationDto inquiryCreationDto) {
        return ResponseEntity.ok(inquiryService.updateInquiry(id,inquiryCreationDto ));
    }

    @DeleteMapping ("/inquiry-delete/{id}")
    public ResponseEntity<String> deleteInquiry(@PathVariable Long id) {
        return ResponseEntity.ok(inquiryService.deleteInquiry(id));
    }


    @PostMapping("/applyJob-creation")
    public ResponseEntity<ApplicantsDto> applyJobCreation(
            @RequestParam Long vacancyId,
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String address,
            @RequestParam String email,
            @RequestParam String city,
            @RequestParam String country,
            @RequestParam int phoneNumber,
            @RequestParam(required = false) MultipartFile resume) throws IOException {

        // Define directory to save resumes

        // Create the directory if it doesn't exist
        File uploadDir = new File(UPLOAD_DIR_RESUMES);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        // Prepare resume path and save file if provided
        String resumePath = null;
        if (resume != null && !resume.isEmpty()) {
            resumePath = UPLOAD_DIR_RESUMES + resume.getOriginalFilename();
            resume.transferTo(new File(resumePath));
        }

        // Build the ApplicantsCreationDto
        ApplicantsCreationDto applicantsCreationDto = ApplicantsCreationDto.builder()
                .vacancyId(vacancyId)
                .firstName(firstName)
                .lastName(lastName)
                .address(address)
                .city(city)
                .country(country)
                .email(email)
                .phoneNumber(phoneNumber)
                .resume(resumePath) // Save the resume path
                .build();

        // Call the service layer to save the applicant
        ApplicantsDto savedApplicant = applicantsService.createApplicants(applicantsCreationDto);

        // Return the response
        return new ResponseEntity<>(savedApplicant, HttpStatus.CREATED);
    }




}
