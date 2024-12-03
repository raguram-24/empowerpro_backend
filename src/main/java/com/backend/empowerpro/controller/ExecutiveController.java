package com.backend.empowerpro.controller;
import com.backend.empowerpro.dto.complaint.ComplaintCreationDto;
import com.backend.empowerpro.dto.complaint.ComplaintDto;
import com.backend.empowerpro.dto.leave.LeaveCreationDto;
import com.backend.empowerpro.dto.leave.LeaveDto;
import com.backend.empowerpro.dto.leave.TodayLeaveDto;
import com.backend.empowerpro.service.ComplaintService;
import com.backend.empowerpro.service.LeaveService;
import com.backend.empowerpro.utils.ReplyRequest;

import com.backend.empowerpro.dto.performanceevaluation.PerformanceEvaluationCreationDto;
import com.backend.empowerpro.dto.performanceevaluation.PerformanceEvaluationDto;
import com.backend.empowerpro.dto.remark.RemarkCreationDto;
import com.backend.empowerpro.dto.remark.RemarkDto;
import com.backend.empowerpro.service.PerformanceEvaluationService;
import com.backend.empowerpro.service.RemarkService;
import com.backend.empowerpro.utils.PerformanceEvaluationMapper;
import com.backend.empowerpro.utils.RemarkMapper;
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
@RequestMapping("/api/v1/executive")
@RequiredArgsConstructor
public class ExecutiveController {

    private final PerformanceEvaluationService performanceEvaluationService;
    private final RemarkService remarkService;
    private final PerformanceEvaluationMapper performanceEvaluationMapper;
    private final RemarkMapper remarkMapper;

    private final LeaveService leaveService;
    private final ComplaintService complaintService;

    private final String UPLOAD_DIR_COMPLAINTS = "C:\\Users\\Insaf\\Desktop\\LatestEmpowerpro\\empowerpro_backend\\uploads\\complaints\\";

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

    @GetMapping("/leave-today")
    public ResponseEntity<List<TodayLeaveDto>> getTodayLeaves() {
        List<TodayLeaveDto> todayLeaves = leaveService.getTodayLeaves();
        return ResponseEntity.ok(todayLeaves);
    }


    /**
     * Enable or disable performance evaluation functionality.
     */
    @PatchMapping("/toggle-performance-evaluation")
    public ResponseEntity<String> togglePerformanceEvaluation(@RequestParam boolean enable) {
        performanceEvaluationService.togglePerformanceEvaluation(enable);
        String status = enable ? "enabled" : "disabled";
        return ResponseEntity.ok("Performance evaluation has been " + status + ".");
    }

    /**
     * Get all performance evaluations.
     */
    @GetMapping("/performance-evaluations")
    public ResponseEntity<List<PerformanceEvaluationDto>> getAllPerformanceEvaluations() {
        List<PerformanceEvaluationDto> evaluations = performanceEvaluationService.getAllEvaluations()
                .stream()
                .map(performanceEvaluationMapper::toPerformanceEvaluationDto)
                .toList();
        return ResponseEntity.ok(evaluations);
    }

    /**
     * Add or update a performance evaluation for an actor.
     */
    @PostMapping("/add-performance-evaluation")
    public ResponseEntity<PerformanceEvaluationDto> addOrUpdatePerformanceEvaluation(
            @RequestBody PerformanceEvaluationCreationDto evaluationDto) {
        PerformanceEvaluationDto updatedEvaluation = performanceEvaluationMapper.toPerformanceEvaluationDto(
                performanceEvaluationService.addOrUpdateEvaluation(
                        evaluationDto.getActorId(), evaluationDto.getEvaluationContent()));
        return ResponseEntity.ok(updatedEvaluation);
    }

    /**
     * Add a remark for another actor.
     */
    @PostMapping("/add-remark")
    public ResponseEntity<RemarkDto> addRemark(@RequestBody RemarkCreationDto remarkCreationDto) {
        RemarkDto createdRemark = remarkMapper.toRemarkDto(
                remarkService.addRemark(
                        remarkCreationDto.getReviewerActorId(),
                        remarkCreationDto.getReviewedActorId(),
                        remarkCreationDto.getContent()));
        return ResponseEntity.ok(createdRemark);
    }

    /**
     * Get all remarks for a specific reviewed actor.
     */
    @GetMapping("/{reviewedActorId}/remarks")
    public ResponseEntity<List<RemarkDto>> getRemarksByReviewedActor(@PathVariable Long reviewedActorId) {
        List<RemarkDto> remarks = remarkService.getRemarksByReviewedActor(reviewedActorId)
                .stream()
                .map(remarkMapper::toRemarkDto)
                .toList();
        return ResponseEntity.ok(remarks);
    }

}
