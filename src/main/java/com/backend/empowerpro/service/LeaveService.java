package com.backend.empowerpro.service;

import com.backend.empowerpro.dto.complaint.ComplaintDto;
import com.backend.empowerpro.dto.leave.LeaveCreationDto;
import com.backend.empowerpro.dto.leave.LeaveDto;
import com.backend.empowerpro.dto.leave.TodayLeaveDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface LeaveService {
    public void saveLeave(LeaveCreationDto leaveCreationDto);
    public List<LeaveDto> getLeavesByUser(Long userId);
    public Integer getAvailableLeaves(Long userId);
    public List<TodayLeaveDto> getTodayLeaves();
}
