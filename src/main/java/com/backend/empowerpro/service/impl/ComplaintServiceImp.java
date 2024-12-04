package com.backend.empowerpro.service.impl;

import com.backend.empowerpro.auth.entity.Employee;
import com.backend.empowerpro.auth.repository.EmployeeRepository;
import com.backend.empowerpro.dto.complaint.ComplaintCreationDto;
import com.backend.empowerpro.dto.complaint.ComplaintDto;
import com.backend.empowerpro.dto.leave.LeaveDto;
import com.backend.empowerpro.entity.Complaint;
import com.backend.empowerpro.exception.ComplaintNotFoundException;
import com.backend.empowerpro.exception.ResourceNotFoundException;
import com.backend.empowerpro.repository.ComplaintRepo;
import com.backend.empowerpro.repository.LeaveRepo;
import com.backend.empowerpro.service.ComplaintService;
import com.backend.empowerpro.service.FileService;
import com.backend.empowerpro.utils.ComplaintMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class ComplaintServiceImp implements ComplaintService {
    private static final Logger logger = LoggerFactory.getLogger(Complaint.class);
    private final ComplaintRepo complaintRepo;
    private final EmployeeRepository employeeRepo;
    private final ComplaintMapper complaintMapper;

    private final FileService fileService;

    @Value("${project.complaints}")
    private String fileUploadDir;

    @Override
    public List<ComplaintDto> getAllComplaints() {
        try{
            List<Complaint> allComplaints = complaintRepo.findAll();
            logger.info("All Complaints has been Fetched Successfully");
            return allComplaints.stream()
                    .map(complaintMapper::toComplaintDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("An unexpected error occurred while fetching complaints: {}", e.getMessage(), e);
            throw new RuntimeException("An unexpected error occurred while fetching complaints", e);
        }
    }

    @Override
    public List<ComplaintDto> getComplaintsToMe() {
        return List.of();
    }

    @Override
    public List<ComplaintDto> getComplaintsFromMyself() {
        return List.of();
    }

    @Override
    public ComplaintDto saveComplaint(ComplaintCreationDto complaintCreationDto) {
        try {

            Employee sender = employeeRepo.findById(complaintCreationDto.getSenderId())
                    .orElseThrow(() -> new RuntimeException("Employee not found with ID: " + complaintCreationDto.getSenderId()));
            Complaint complaint = complaintMapper.toComplaint(complaintCreationDto);
            complaint.setSender(sender);

            Complaint savedComplaint = complaintRepo.save(complaint);

            logger.info("Complaint has been created successfully: {}", savedComplaint);

            // Convert the saved Complaint entity to ComplaintDto to return
            return complaintMapper.toComplaintDto(savedComplaint);

        }catch (Exception e) {
            logger.error("An unexpected error occurred while creating complaint: {}", e.getMessage(), e);
            throw new RuntimeException("An unexpected error occurred while creating complaint", e);
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
    public String deleteComplaint(Long id) {
        try{
            //Fetching All Vacancies
            Optional<Complaint> complaint = complaintRepo.findById(id);
            if(complaint.isPresent()){
                complaintRepo.deleteById(id);
                logger.info("complaint of {} has been deleted Successfully", id);
                return "Deleted Successfully";
            }else{
                throw new ComplaintNotFoundException("Not found");
            }
        } catch (Exception e) {
            logger.error("An unexpected error occurred while deleting complaints: {}", e.getMessage(), e);
            throw new RuntimeException("An unexpected error occurred while deleting complaints", e);
        }
    }


    @Override
    public List<ComplaintDto> getComplaintsAssignedToUser(Long userId) {
        try{
            List<Complaint> complaints = complaintRepo.findBySender_Id(userId);
            logger.info("All Complaints has been Fetched Successfully");
            return complaints.stream()
                    .map(complaintMapper::toComplaintDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("An unexpected error occurred while fetching complaints: {}", e.getMessage(), e);
            throw new RuntimeException("An unexpected error occurred while fetching complaints", e);
        }
    }

    @Override
    @Transactional
    public void replyToComplaint(Long complaintId, String reply) {
        // Fetch the complaint by ID
        Complaint complaint = complaintRepo.findById(complaintId)
                .orElseThrow(() -> new ResourceNotFoundException("Complaint not found with id: " + complaintId));

        // Update the complaint with the reply and status
        complaint.setReply(reply);
        complaint.setStatus("SOLVED");

        // Save the updated complaint
        complaintRepo.save(complaint);
    }


    public List<ComplaintDto> getComplaintsAssignedToRole(String role) {
        try{
            List<Complaint> complaints = complaintRepo.findByAssignedTo(role);
            logger.info("All Complaints has been Fetched Successfully");
            return complaints.stream()
                    .map(complaintMapper::toComplaintDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("An unexpected error occurred while fetching complaints: {}", e.getMessage(), e);
            throw new RuntimeException("An unexpected error occurred while fetching complaints", e);
        }
    }


}
