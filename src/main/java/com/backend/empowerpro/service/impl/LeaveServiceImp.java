package com.backend.empowerpro.service.impl;

import com.backend.empowerpro.auth.entity.Employee;
import com.backend.empowerpro.auth.entity.EmployeeRole;
import com.backend.empowerpro.auth.repository.EmployeeRepository;
import com.backend.empowerpro.dto.leave.LeaveCreationDto;
import com.backend.empowerpro.dto.leave.LeaveDto;
import com.backend.empowerpro.dto.leave.LeaveHrDto;
import com.backend.empowerpro.dto.leave.TodayLeaveDto;
import com.backend.empowerpro.entity.*;
import com.backend.empowerpro.exception.ComplaintNotFoundException;
import com.backend.empowerpro.exception.LeaveNotFoundException;
import com.backend.empowerpro.exception.VacancyNotFoundException;
import com.backend.empowerpro.repository.LeaveBalanceRepo;
import com.backend.empowerpro.repository.LeaveRepo;
import com.backend.empowerpro.service.LeaveService;
import com.backend.empowerpro.utils.LeavesMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class LeaveServiceImp implements LeaveService {
    private static final Logger logger = LoggerFactory.getLogger(Leave.class);
    private final EmployeeRepository employeeRepository;
    private final LeaveRepo leaveRepo;
    private final LeaveBalanceRepo leaveBalanceRepo;
    private final LeavesMapper leavesMapper;

    @Override
    public void saveLeave(LeaveCreationDto leaveCreationDto) {
        Employee employee = employeeRepository.findById(leaveCreationDto.getSenderId())
                .orElseThrow(() -> new EntityNotFoundException("Employee with ID " + leaveCreationDto.getSenderId() + " not found"));

        // Calculate leave days
        long leaveDays = ChronoUnit.DAYS.between(
                leaveCreationDto.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                leaveCreationDto.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
        ) + 1;

        // Map the LeaveCreationDto to the Leave entity
        Leave leave = Leave.builder()
                .employee(employee) // Set employee fetched from DB
                .leaveType(LeaveType.valueOf(leaveCreationDto.getLeaveType().toUpperCase()))
                .startDate(leaveCreationDto.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                .endDate(leaveCreationDto.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                .leaveDays((int) leaveDays)
                .reason(leaveCreationDto.getReason())
                .status(LeaveStatus.PENDING) // Default status
                .build();

        // Save the leave to the repository
        leaveRepo.save(leave);
    }

    @Override
    public List<LeaveDto> getLeavesByUser(Long userId) {
        try{
            List<Leave> leaves = leaveRepo.findByEmployee_Id(userId);
            logger.info("All leaves has been fetched successfully");
            return leaves.stream()
                    .map(leavesMapper::toLeaveDto)
                    .collect(Collectors.toList());
        }catch (Exception e){
            logger.error("An error occured while fetching leaves");
            throw new RuntimeException("An unexcepted error occured", e);
        }

    }

    @Override
    public List<TodayLeaveDto> getTodayLeaves() {
        LocalDate today = LocalDate.now();
        List<Object[]> todayLeaves = leaveRepo.findLeavesForToday(today);

        return todayLeaves.stream()
                .map(result -> TodayLeaveDto.builder()
                        .endDate((LocalDate) result[0])                  // Extract endDate
                        .employeeId((Long) result[1])                   // Extract employeeId
                        .employeeName(result[2] + " " + result[3])       // Combine firstName and lastName
                        .role((String) result[4])
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public List<LeaveHrDto> getLeavesByAssignRole(Long loggedInUser) {
        try{
            List<Leave> leaves = leaveRepo.findLeavesAssignedToRole(loggedInUser);
            logger.info("All leaves have been fetched successfully");
            return leaves.stream()
                    .map(leave -> LeaveHrDto.builder()
                            .id(leave.getId())
                            .senderId(leave.getEmployee().getId())  // Assuming employee's ID is the sender
                            .employeeName(leave.getEmployee().getFirstName() + " " + leave.getEmployee().getLastName())  // Assuming there's a method for full name
                            .leaveType(leave.getLeaveType().toString())  // Convert Enum to String
                            .leaveDays(leave.getLeaveDays())
                            .startDate(java.sql.Date.valueOf(leave.getStartDate()))  // Convert LocalDate to java.sql.Date
                            .endDate(java.sql.Date.valueOf(leave.getEndDate()))  // Convert LocalDate to java.sql.Date
                            .reason(leave.getReason())
                            .status(leave.getStatus().toString())  // Convert Enum to String
                            .build())  // Finalize the object creation
                    .collect(Collectors.toList());

        } catch (Exception e) {
            logger.error("An error occurred while fetching leaves", e);
            throw new RuntimeException("An unexpected error occurred", e);
        }

    }

    @Override
    public LeaveHrDto getOneRequestLeave(Long id) {
        try{
            Optional<Leave> leave = leaveRepo.findById(id);

            if(leave.isPresent()){
                Leave leaveResult = leave.get();
                logger.info("leave has been fetch successfully with id ", id);

                return LeaveHrDto.builder()
                        .id(leaveResult.getId())
                        .senderId(leaveResult.getEmployee().getId())
                        .employeeName(leaveResult.getEmployee().getFirstName() + " " + leaveResult.getEmployee().getLastName())
                        .leaveType(leaveResult.getLeaveType().toString())
                        .leaveDays(leaveResult.getLeaveDays())
                        .startDate(java.sql.Date.valueOf(leaveResult.getStartDate())) // Convert LocalDate to java.sql.Date
                        .endDate(java.sql.Date.valueOf(leaveResult.getEndDate())) // Convert LocalDate to java.sql.Date
                        .reason(leaveResult.getReason())
                        .role(leaveResult.getEmployee().getRole().name())
                        .comment(leaveResult.getComment())
                        .status(leaveResult.getStatus().toString()) // Convert Enum to String
                        .build();
            }else{
                throw new LeaveNotFoundException("Leave with ID " + id + " not found");
            }
        }catch (Exception e){
            logger.error("An unexpected error occurred while fetching leave: {}", e.getMessage(), e);
            throw new RuntimeException("An unexpected error occurred while fetching leave", e);
        }
    }

    @Override
    public void setLeaveRejected(Long id, String comment) {
        try {
            Optional<Leave> leaveOptional = leaveRepo.findById(id);
            if (leaveOptional.isPresent()) {
                Leave leave = leaveOptional.get();

                // Ensure the leave exists and can be rejected
                leaveRepo.updateLeaveStatus(id, LeaveStatus.REJECTED, comment);
                logger.info("Leave with ID {} has been rejected successfully", id);
            } else {
                throw new LeaveNotFoundException("Leave not found for ID: " + id);
            }
        } catch (Exception e) {
            logger.error("An error occurred while rejecting leave with ID {}: {}", id, e.getMessage(), e);
            throw new RuntimeException("Failed to reject leave", e);
        }
    }

    @Override
    public void setLeaveApproved(Long id, String comment) {
        try {
            Optional<Leave> leaveOptional = leaveRepo.findById(id);
            if (leaveOptional.isPresent()) {
                Leave leave = leaveOptional.get();

                // Ensure the leave exists and can be rejected
                leaveRepo.updateLeaveStatus(id, LeaveStatus.APPROVED, comment);
                logger.info("Leave with ID {} has been approved successfully", id);
            } else {
                throw new LeaveNotFoundException("Leave not found for ID: " + id);
            }
        } catch (Exception e) {
            logger.error("An error occurred while approving leave with ID {}: {}", id, e.getMessage(), e);
            throw new RuntimeException("Failed to approve leave", e);
        }
    }

    @Override
    public List<LeaveDto> getAllLeaves() {
        try{
            List<Leave> leaves = leaveRepo.findAll();
            logger.info("All leaves has been fetched successfully");
            return leaves.stream()
                    .map(leavesMapper::toLeaveDto)
                    .collect(Collectors.toList());
        }catch (Exception e){
            logger.error("An error occured while fetching leaves");
            throw new RuntimeException("An unexcepted error occured", e);
        }
    }

}
