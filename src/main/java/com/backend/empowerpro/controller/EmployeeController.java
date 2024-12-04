package com.backend.empowerpro.controller;

import com.backend.empowerpro.dto.employee.EmployeeCreationDto;
import com.backend.empowerpro.dto.employee.EmployeeDto;
import com.backend.empowerpro.entity.PayRoll;
import com.backend.empowerpro.service.EmployeeService;
import com.backend.empowerpro.service.PayrollService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.backend.empowerpro.dto.complaint.ComplaintCreationDto;
import com.backend.empowerpro.dto.complaint.ComplaintDto;
import com.backend.empowerpro.dto.leave.LeaveCreationDto;
import com.backend.empowerpro.dto.leave.LeaveDto;
import com.backend.empowerpro.dto.leave.TodayLeaveDto;
import com.backend.empowerpro.service.ComplaintService;
import com.backend.empowerpro.service.LeaveService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;
    private final LeaveService leaveService;
    private final ComplaintService complaintService;
    private final PayrollService payrollService;

    private final String UPLOAD_DIR_COMPLAINTS = "C:\\Users\\Insaf\\Desktop\\LatestEmpowerpro\\empowerpro_backend\\uploads\\complaints\\";

    // ==================== Employee CRUD ====================



    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }

    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeCreationDto employeeCreationDto) {
        return ResponseEntity.ok(employeeService.createEmployee(employeeCreationDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable Long id, @RequestBody EmployeeCreationDto employeeCreationDto) {
        return ResponseEntity.ok(employeeService.updateEmployee(id, employeeCreationDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok("Employee deleted successfully.");
    }

    // ==================== Complaint Management ====================

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
        return ResponseEntity.status(201).body(savedComplaint);
    }

    @GetMapping("/complaint/{id}")
    public ResponseEntity<ComplaintDto> getOneComplaint(@PathVariable Long id) {
        return ResponseEntity.ok(complaintService.getOneComplaint(id));
    }

    @DeleteMapping("/complaint-delete/{id}")
    public ResponseEntity<String> deleteComplaint(@PathVariable Long id) {
        return ResponseEntity.ok(complaintService.deleteComplaint(id));
    }

    @GetMapping("/complaint-file")
    public ResponseEntity<Resource> getComplaintFile(@RequestParam String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) return ResponseEntity.notFound().build();
        Path path = file.toPath();
        Resource resource = new UrlResource(path.toUri());
        String contentType = Files.probeContentType(path);
        contentType = (contentType != null) ? contentType : "application/octet-stream";
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + file.getName() + "\"")
                .body(resource);
    }

    // ==================== Leave Management ====================

    @PostMapping("/leave-creation")
    public ResponseEntity<String> applyLeave(@RequestBody LeaveCreationDto leaveCreationDto) {
        leaveService.saveLeave(leaveCreationDto);
        return ResponseEntity.ok("Leave applied successfully!");
    }

    @GetMapping("/leave/{userId}")
    public ResponseEntity<List<LeaveDto>> getAllLeavesByUserId(@PathVariable Long userId) {
        List<LeaveDto> leaves = leaveService.getLeavesByUser(userId);
        return ResponseEntity.ok(leaves);
    }

    @GetMapping("/available-leaves/{userId}")
    public ResponseEntity<Integer> getAvailableLeaves(@PathVariable Long userId) {
        return ResponseEntity.ok(leaveService.getAvailableLeaves(userId));
    }

    @GetMapping("/leave-today")
    public ResponseEntity<List<TodayLeaveDto>> getTodayLeaves() {
        return ResponseEntity.ok(leaveService.getTodayLeaves());
    }
    @PreAuthorize("hasAuthority('Employee')")
    @GetMapping("/get-slip/{id}")
    public ResponseEntity<PayRoll> getOnePayRoll(@PathVariable Long id){
        return ResponseEntity.ok(payrollService.getOnePayRoll(id));
    }
}
