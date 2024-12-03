package com.backend.empowerpro.service;

import com.backend.empowerpro.dto.complaint.ComplaintCreationDto;
import com.backend.empowerpro.dto.complaint.ComplaintDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ComplaintService {
    public List<ComplaintDto> getAllComplaints();
    public List<ComplaintDto> getComplaintsToMe();
    public List<ComplaintDto> getComplaintsFromMyself();
    public ComplaintDto saveComplaint(ComplaintCreationDto complaintCreationDto);
    public ComplaintDto getOneComplaint(Long id);
    public String deleteComplaint(Long id);
    public List<ComplaintDto> getComplaintsAssignedToUser(Long userId);
    void replyToComplaint(Long complaintId, String reply);
    public List<ComplaintDto> getComplaintsAssignedToRole(String role);
}
