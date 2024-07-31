package com.backend.empowerpro.service;

import com.backend.empowerpro.dto.complaint.ComplaintDto;
import com.backend.empowerpro.entity.Complaint;

import java.util.List;

public interface ComplaintService {
    List<ComplaintDto> getAllComplaints();

    List<ComplaintDto> getComplaintsToMe();
    List<ComplaintDto> getComplaintsFromMyself();
    String createComplaint(ComplaintDto complaintDto);
    ComplaintDto getOneComplaint(Long id);
    ComplaintDto updateComplaint(Long id, ComplaintDto complaintDto);
    String deleteComplaint(Long id);
}
