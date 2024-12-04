package com.backend.empowerpro.service;

import com.backend.empowerpro.dto.complaint.ComplaintDto;
import com.backend.empowerpro.dto.leave.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

public interface LeaveService {
    public void saveLeave(LeaveCreationDto leaveCreationDto);
    public List<LeaveDto> getLeavesByUser(Long userId);
    public List<TodayLeaveDto> getTodayLeaves();
    public List<LeaveHrDto> getLeavesByAssignRole(Long loggedInUser);
    public LeaveHrDto getOneRequestLeave(Long id);
    public void setLeaveRejected(Long id, String comment);
    public void setLeaveApproved(Long id, String comment);
    public List<LeaveDto> getAllLeaves();
}
