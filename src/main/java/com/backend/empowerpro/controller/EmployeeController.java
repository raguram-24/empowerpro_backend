package com.backend.empowerpro.controller;

import com.backend.empowerpro.auth.utils.EmployeeUpdateRequest;
import com.backend.empowerpro.dto.attendance.CheckoutAttendanceDto;
import com.backend.empowerpro.dto.attendance.CreateAttendanceDto;
import com.backend.empowerpro.dto.complaint.ComplaintCreationDto;
import com.backend.empowerpro.dto.complaint.ComplaintDto;
import com.backend.empowerpro.dto.leave.LeaveCreationDto;
import com.backend.empowerpro.dto.leave.LeaveDto;
import com.backend.empowerpro.dto.leave.TodayLeaveDto;
import com.backend.empowerpro.entity.Attendance;
import com.backend.empowerpro.service.AttendanceService;
import com.backend.empowerpro.service.ComplaintService;
import com.backend.empowerpro.service.EmployeeService;
import com.backend.empowerpro.service.LeaveService;
import com.backend.empowerpro.utils.ReplyRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/v1/employee")
@RequiredArgsConstructor

public class EmployeeController {

    private final EmployeeService employeeService;
    private final AttendanceService attendanceService;
    private final LeaveService leaveService;
    private final ComplaintService complaintService;

    private final String UPLOAD_DIR_COMPLAINTS = "C:\\Users\\Insaf\\Desktop\\LatestEmpowerpro\\empowerpro_backend\\uploads\\complaints\\";

    private EmployeeUpdateRequest convertToEmployeeUpdateRequest(String employeeUpdateReq) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(employeeUpdateReq, EmployeeUpdateRequest.class);
    }

    @PostMapping("/leave-creation")
    public ResponseEntity<String> applyLeave(@RequestBody LeaveCreationDto leaveCreationDto) {
        leaveService.saveLeave(leaveCreationDto);
        return ResponseEntity.ok("Leave applied successfully!");
    }

    @PostMapping("/complaint-creation")
    public ResponseEntity<ComplaintDto> createComplaint(
            @RequestParam Long senderId,
            @RequestParam String about,
            @RequestParam String assignedTo,
            @RequestParam String description,
            @RequestParam(required = false) MultipartFile file) throws IOException {

        ComplaintCreationDto complaintCreationDto = ComplaintCreationDto.builder()
                .status("PENDING")
                .senderId(senderId)
                .about(about)
                .assignedTo(assignedTo)
                .description(description)
                .date(new Date())
                .build();

        if (file != null && !file.isEmpty()) {
            String filePath = UPLOAD_DIR_COMPLAINTS + file.getOriginalFilename();
            file.transferTo(new File(filePath));
            complaintCreationDto.setFilesToUpload(filePath);
        }

        ComplaintDto savedComplaint = complaintService.saveComplaint(complaintCreationDto);
        return new ResponseEntity<>(savedComplaint, HttpStatus.CREATED);
    }

    @GetMapping("/complaint-get-one/{id}")
    public ResponseEntity<ComplaintDto> getOneComplaint(@PathVariable Long id) {
        return ResponseEntity.ok(complaintService.getOneComplaint(id));
    }

    @DeleteMapping ("/complaint-delete/{id}")
    public ResponseEntity<String> deleteComplaint(@PathVariable Long id) {
        return ResponseEntity.ok(complaintService.deleteComplaint(id));
    }

    @GetMapping("/assigned-to-hr")
    public ResponseEntity<List<ComplaintDto>> getComplaintsAssignedToHR() {
        List<ComplaintDto> complaints = complaintService.getComplaintsAssignedToHR();
        return ResponseEntity.ok(complaints);
    }

    //    @PostMapping("/complaint-creation")
    //    public ResponseEntity<ComplaintDto> saveComplaint(@RequestBody ComplaintCreationDto complaintCreationDto) {
    //        ComplaintDto savedComplaint = complaintService.saveComplaint(complaintCreationDto);
    //        return ResponseEntity.status(HttpStatus.CREATED).body(savedComplaint);
    //    }

//    @GetMapping("/complaint")
//    public ResponseEntity<List<ComplaintDto>> getComplaintsAssignedToUser(){
//        List<ComplaintDto> complaints = complaintService.getComplaintsAssignedToUser(1L);
//        return ResponseEntity.ok(complaints);
//    }

    @GetMapping("/complaint/{userId}")
    public ResponseEntity<List<ComplaintDto>> getAllComplaintsByEmployeeId(@PathVariable Long userId) {
        List<ComplaintDto> complaints = complaintService.getComplaintsAssignedToUser(userId);
        if (complaints.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(complaints);
    }

    @GetMapping("/complaint-file")
    public ResponseEntity<Resource> getComplaintFile(@RequestParam String filePath) throws IOException {
        try {
            if (filePath == null || filePath.isEmpty()) {
                return ResponseEntity.badRequest().body(null);
            }

            File file = new File(filePath);
            if (!file.exists()) {
                return ResponseEntity.notFound().build();
            }

            Path path = file.toPath();
            Resource resource = new UrlResource(path.toUri());

            // Detect the file's content type (e.g., PDF, image, etc.)
            String contentType = Files.probeContentType(path);
            if (contentType == null) {
                contentType = "application/octet-stream"; // Fallback type
            }

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + file.getName() + "\"")
                    .body(resource);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @PostMapping("/complaint-reply/{id}")
    public ResponseEntity<String> replyToComplaint(
            @PathVariable Long id,
            @RequestBody ReplyRequest replyRequest) {
        complaintService.replyToComplaint(id, replyRequest.getReply());
        return ResponseEntity.ok("Reply sent successfully!");
    }

    @GetMapping("/leave/{userId}")
    public ResponseEntity<List<LeaveDto>> getAllLeavesByUserId(@PathVariable Long userId) {
        List<LeaveDto> leaves = leaveService.getLeavesByUser(userId);
        if (leaves.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(leaves);
    }

    @GetMapping("/available-leaves/{userId}")
    public ResponseEntity<Integer> getAllLeavesByUser(@PathVariable Long userId) {
        int availableLeaves = leaveService.getAvailableLeaves(userId);
        return ResponseEntity.ok(availableLeaves); // Wrap the integer in ResponseEntity with HTTP 200 status
    }

    @GetMapping("/leave-today")
    public ResponseEntity<List<TodayLeaveDto>> getTodayLeaves() {
        List<TodayLeaveDto> todayLeaves = leaveService.getTodayLeaves();
        return ResponseEntity.ok(todayLeaves);
    }


    @GetMapping("/leave-get-filtered")
    public ResponseEntity<List<LeaveDto>> getAllLeaves(
            @RequestParam(required = false) String timePeriod,
            @RequestParam(required = false) String status) {
        List<LeaveDto> leaves = leaveService.getLeavesByFilter(timePeriod, status);
        return ResponseEntity.ok(leaves);
    }

    @PostMapping("/createAttendance")
    public ResponseEntity<Attendance> createAttendance(
            @RequestBody CreateAttendanceDto createAttendanceDto
    ){
      Attendance attendance =   attendanceService.createAttendance(createAttendanceDto);
      return ResponseEntity.ok(attendance);
    }

    @PostMapping("/checkoutAttendance/{id}")
    public ResponseEntity<Attendance> checkoutAttendance(
            @RequestBody CheckoutAttendanceDto checkoutAttendanceDto,
            @PathVariable("id") Long id
    ){

        Attendance attendance = attendanceService.checkoutUpdateAttendance(checkoutAttendanceDto,id);
        return ResponseEntity.ok(attendance);
    }

    @GetMapping("/getAllAttendance")
    public ResponseEntity<List<Attendance>> getAllAttendance() {
        List<Attendance> attendance = attendanceService.getAllAttendance();
        return ResponseEntity.ok(attendance);
    }


}
