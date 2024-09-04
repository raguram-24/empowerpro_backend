package com.backend.empowerpro.service;

import com.backend.empowerpro.dto.leave.LeaveCreationDto;
import com.backend.empowerpro.dto.leave.LeaveDto;
import com.backend.empowerpro.dto.vacancy.VacancyCreationDto;
import com.backend.empowerpro.dto.vacancy.VacancyDto;

import java.util.List;

public interface LeaveService {
    public List<LeaveDto> getAllLeaves();
    public String createLeave(LeaveCreationDto leaveCreationDto);
    public LeaveDto getOneLeave(Long id);
    public LeaveDto updateLeave(Long id, LeaveCreationDto leaveCreationDto);
    public String deleteLeave(Long id);
}
