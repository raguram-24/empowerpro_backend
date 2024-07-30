package com.backend.empowerpro.service.impl;

import com.backend.empowerpro.dto.vacancy.VacancyCreationDto;
import com.backend.empowerpro.dto.vacancy.VacancyDto;
import com.backend.empowerpro.entity.Vacancy;
import com.backend.empowerpro.exception.VacancyNotFoundException;
import com.backend.empowerpro.repository.VacancyRepo;
import com.backend.empowerpro.service.EmployeeService;
import com.backend.empowerpro.service.VacancyService;
import com.backend.empowerpro.utils.VacancyMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ComplaintServiceImp implements ComplaintService {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);
    private final ComplaintRepo complaintRepo;
    private final ComplaintMapper complaintMapper;
    // @Override
    // public List<ComplaintDto> getAllComplaints() {
    //     try{
            
    //         List<Complaint> allComplaints = complaintRepo.findAll();
    //         logger.info("All complaints has been Fetched Successfully");
    //         return allVacancies.stream()
    //                 .map(complaintMapper::toComplaintDto)
    //                 .collect(Collectors.toList());
    //         } catch (Exception e) {
    //         logger.error("An unexpected error occurred while fetching vacancies: {}", e.getMessage(), e);
    //         throw new RuntimeException("An unexpected error occurred while fetching vacancies", e);
    //     }
    // }

     @Override
    public List<ComplaintDto> getComplaintsToMe() {
        try {
            String currentUser = getCurrentUser(); // Implement this method to get the currently logged-in user
            List<Complaint> complaintsToMe = complaintRepo.findByAssignedTo(currentUser);
            logger.info("Fetched complaints assigned to {}", currentUser);
            return complaintsToMe.stream()
                    .map(complaintMapper::toComplaintDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("An unexpected error occurred while fetching complaints assigned to me: {}", e.getMessage(), e);
            throw new RuntimeException("An unexpected error occurred while fetching complaints assigned to me", e);
        }
    }

    @Override
    public List<ComplaintDto> getComplaintsFromMyself() {
        try {
            String currentUser = getCurrentUser(); // Implement this method to get the currently logged-in user
            List<Complaint> complaintsFromMyself = complaintRepo.findBySender(currentUser);
            logger.info("Fetched complaints sent by {}", currentUser);
            return complaintsFromMyself.stream()
                    .map(complaintMapper::toComplaintDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("An unexpected error occurred while fetching complaints sent by me: {}", e.getMessage(), e);
            throw new RuntimeException("An unexpected error occurred while fetching complaints sent by me", e);
        }
    }

    @Override
    public String createComplaint(complaintDto complaintDto) {
        try{
            Complaint complaint = complaintMapper.toComplaint(complaintDto);
            complaintRepo.save(complaint);
            logger.info("Complaints has been created Successfully {}",vacancy);
            return "Successfully Created";
        }catch (Exception e){
            logger.error("An unexpected error occurred while creating vacancy: {}", e.getMessage(), e);
            throw new RuntimeException("An unexpected error occurred while creating vacancy", e);
        }
    }

    @Override
    public ComplaintDto getOneComplaint(Long id) {
        try{
            //Fetching All Vacancies
            Optional<Complaint> complaint = complaintRepo.findById(id);
            if(complaint.isPresent()){
                Complaint complaintResult = complaint.get();
                logger.info("complaint of {} has been fetched Successfully", id);
                return complaintMapper.toComplaintDto(complaintResult);
            }else{
                throw new ComplaintNotFoundException("Not found");
            }
        } catch (Exception e) {
            logger.error("An unexpected error occurred while fetching complaints: {}", e.getMessage(), e);
            throw new RuntimeException("An unexpected error occurred while fetching complaints", e);
        }
    }

    @Override
public ComplaintDto updateComplaint(Long id, ComplaintDto complaintDto) {
    try {
        Optional<Complaint> complaintOptional = complaintRepo.findById(id);
        if (complaintOptional.isPresent()) {
            Complaint complaint = complaintOptional.get();
            logger.info("Complaint with id {} has been fetched successfully", id);

            // Update fields from ComplaintDto to Complaint entity
            complaint.setStatus(complaintDto.getStatus());
            complaint.setAbout(complaintDto.getAbout());
            complaint.setDate(complaintDto.getDate());
            complaint.setSender(complaintDto.getSender());
            complaint.setAssignedTo(complaintDto.getAssignedTo());
            complaint.setDescription(complaintDto.getDescription());
            complaint.setReply(complaintDto.getReply());
            complaint.setFilesToUpload(complaintDto.getFilesToUpload());

            // Save the updated complaint entity
            Complaint updatedComplaint = complaintRepo.save(complaint);
            logger.info("Complaint with id {} has been updated successfully", id);

            // Map the updated complaint entity to ComplaintDto
            return complaintMapper.toComplaintDto(updatedComplaint);
        } else {
            logger.error("Complaint with id {} not found", id);
            throw new ComplaintNotFoundException("Complaint not found");
        }
    } catch (Exception e) {
        logger.error("An unexpected error occurred while updating complaint: {}", e.getMessage(), e);
        throw new RuntimeException("An unexpected error occurred while updating complaint", e);
    }
}


    @Override
    public String deleteComplaint(Long id) {
        try {
            Optional<Complaint> complaintOptional = complaintRepo.findById(id);
            if (complaintOptional.isPresent()) {
                complaintRepo.deleteById(id);
                logger.info("Complaint with id {} has been deleted successfully", id);
                return "Deleted Successfully";
            } else {
                throw new ComplaintNotFoundException("Complaint not found");
            }
        } catch (Exception e) {
            logger.error("An unexpected error occurred while deleting complaint: {}", e.getMessage(), e);
            throw new RuntimeException("An unexpected error occurred while deleting complaint", e);
        }
    }

}
