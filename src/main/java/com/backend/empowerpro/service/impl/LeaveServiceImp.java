package com.backend.empowerpro.service.impl;

import com.backend.empowerpro.auth.entity.Employee;
import com.backend.empowerpro.auth.entity.EmployeeRole;
import com.backend.empowerpro.auth.repository.EmployeeRepository;
import com.backend.empowerpro.dto.leave.LeaveCreationDto;
import com.backend.empowerpro.dto.leave.LeaveDto;
import com.backend.empowerpro.dto.leave.TodayLeaveDto;
import com.backend.empowerpro.entity.*;
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
//import java.time.temporal.ChronoUnit;
import java.util.List;
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
//        Employee employee = employeeRepository.findById(leaveCreationDto.getSenderId())
//                .orElseThrow(() -> new EntityNotFoundException("Employee with ID " + leaveCreationDto.getSenderId() + " not found"));

        // Calculate leave days
//        long leaveDays = ChronoUnit.DAYS.between(
//                leaveCreationDto.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
//                leaveCreationDto.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
//        ) + 1;
//
//        // Map the LeaveCreationDto to the Leave entity
//        Leave leave = Leave.builder()
//                .employee(employee) // Set employee fetched from DB
//                .leaveType(LeaveType.valueOf(leaveCreationDto.getLeaveType().toUpperCase()))
//                .startDate(leaveCreationDto.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
//                .endDate(leaveCreationDto.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
//                .leaveDays((int) leaveDays)
//                .reason(leaveCreationDto.getReason())
//                .status(LeaveStatus.PENDING) // Default status
//                .build();
//
//        // Save the leave to the repository
//        leaveRepo.save(leave);
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
    public Integer getAvailableLeaves(Long userId) {
        return leaveBalanceRepo.findByEmployee_Id(userId)
                .map(LeaveBalance::getTotalAvailableLeaves) // Assuming a getter method in LeaveBalance
                .orElseThrow(() -> new EntityNotFoundException("Leave balance not found for Employee ID: " + userId));
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
    public List<LeaveDto> getLeavesByFilter(String timePeriod, String status){
//        LocalDate startDate = calculateStartDate(timePeriod);
//
//        try{
//            List<Leave> filteredLeaves = leaveRepo.findByStatusAndDateRange(status, startDate);
//            logger.info("All Filtered leaves has been Fetched Successfully");
//            return filteredLeaves.stream()
//                    .map(leavesMapper::toLeaveDto)
//                    .collect(Collectors.toList());
//        } catch (Exception e) {
//            logger.error("An unexpected error occurred while fetching filtered leaves: {}", e.getMessage(), e);
//            throw new RuntimeException("An unexpected error occurred while fetching filtered leaves", e);
//        }
        return null;
    }

    private LocalDate calculateStartDate(String timePeriod){
        if ("Last 3 Months".equals(timePeriod)){
            return LocalDate.now().minusMonths(3);
        } else if ("Last 6 Months".equals(timePeriod)) {
            return LocalDate.now().minusMonths(6);
        } else if ("Last 12 Months".equals(timePeriod)) {
            return LocalDate.now().minusMonths(12);
        }

        return LocalDate.now().minusDays(30);
    }


}
