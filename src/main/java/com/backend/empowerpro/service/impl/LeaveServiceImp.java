package com.backend.empowerpro.service.impl;

import com.backend.empowerpro.dto.leave.LeaveCreationDto;
import com.backend.empowerpro.dto.leave.LeaveDto;
import com.backend.empowerpro.entity.Leave;
import com.backend.empowerpro.exception.LeaveNotFoundException;
import com.backend.empowerpro.repository.LeaveRepo;
import com.backend.empowerpro.service.LeaveService;
import com.backend.empowerpro.utils.LeaveMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class LeaveServiceImp implements LeaveService {
    private static final Logger logger = LoggerFactory.getLogger(LeaveService.class);
    private final LeaveRepo leaveRepo;
    private final LeaveMapper leaveMapper;

    @Override
    public List<LeaveDto> getAllLeaves() {
        try{
            List<Leave> allLeaves = leaveRepo.findAll();
            logger.info("All leaves has been Fetched Successfully");
            return allLeaves.stream()
                    .map(leaveMapper::toLeaveDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("An unexpected error occurred while fetching vacancies: {}", e.getMessage(), e);
            throw new RuntimeException("An unexpected error occurred while fetching vacancies", e);
        }
    }

    @Override
    public String createLeave(LeaveCreationDto leaveCreationDto) {
        try{
            Leave leave = leaveMapper.toLeave(leaveCreationDto);
            leaveRepo.save(leave);
            logger.info("leave has been created Successfully {}",leave);
            return "Successfully Created";
        }catch (Exception e){
            logger.error("An unexpected error occurred while creating vacancy: {}", e.getMessage(), e);
            throw new RuntimeException("An unexpected error occurred while creating vacancy", e);
        }
    }

    @Override
    public LeaveDto getOneLeave(Long id) {
        try{
            Optional<Leave> leave = leaveRepo.findById(id);
            if(leave.isPresent()){
                Leave leaveResult = leave.get();
                logger.info("leave of {} has been fetched Successfully", id);
                return leaveMapper.toLeaveDto(leaveResult);
            }else{
                throw new LeaveNotFoundException("Not found");
            }
        } catch (Exception e) {
            logger.error("An unexpected error occurred while fetching vacancies: {}", e.getMessage(), e);
            throw new RuntimeException("An unexpected error occurred while fetching vacancies", e);
        }
    }

    @Override
    public LeaveDto updateLeave(Long id , LeaveCreationDto leaveCreationDto) {
        try{
            Optional<Leave> leave = leaveRepo.findById(id);
            if(leave.isPresent()){
                Leave leaveResult = leave.get();
                logger.info("vacancy of {} has been fetched Successfully", id);
                leaveResult.setLeaveType(leaveCreationDto.getLeaveType());
                leaveResult.setStartDate(leaveCreationDto.getStartDate());
                leaveResult.setEndDate(leaveCreationDto.getEndDate());
                leaveResult.setReason(leaveCreationDto.getReason());
                leaveResult.setStatus(leaveCreationDto.getStatus());

                Leave result = leaveRepo.save(leaveResult);
                logger.info("vacancy of {} has been Updated Successfully", id);
                return leaveMapper.toLeaveDto(result);
            }else{
                logger.error("Not found leave {}",id);
                throw new LeaveNotFoundException("Not found");
            }

        } catch (Exception e) {
            logger.error("An unexpected error occurred while Updating vacancies: {}", e.getMessage(), e);
            throw new RuntimeException("An unexpected error occurred while Updating vacancies", e);
        }
    }

    @Override
    public String deleteLeave(Long id) {
        try{
            //Fetching All Vacancies
            Optional<Leave> leave = leaveRepo.findById(id);
            if(leave.isPresent()){
                leaveRepo.deleteById(id);
                logger.info("vacancy of {} has been deleted Successfully", id);
                return "Deleted Successfully";
            }else{
                throw new LeaveNotFoundException("Not found");
            }
        } catch (Exception e) {
            logger.error("An unexpected error occurred while deleting vacancies: {}", e.getMessage(), e);
            throw new RuntimeException("An unexpected error occurred while deleting vacancies", e);
        }
    }
}
