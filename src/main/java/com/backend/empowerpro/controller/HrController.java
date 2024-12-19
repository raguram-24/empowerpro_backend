package com.backend.empowerpro.controller;

import com.backend.empowerpro.dto.complaint.ComplaintCreationDto;
import com.backend.empowerpro.dto.complaint.ComplaintDto;
import com.backend.empowerpro.dto.leave.LeaveCreationDto;
import com.backend.empowerpro.dto.leave.LeaveDto;
import com.backend.empowerpro.dto.leave.LeaveHrDto;
import com.backend.empowerpro.dto.leave.TodayLeaveDto;
import com.backend.empowerpro.dto.events.EventCreationDto;
import com.backend.empowerpro.dto.events.EventDto;
import com.backend.empowerpro.dto.medicalClaim.MedicalClaimCreation;
import com.backend.empowerpro.dto.medicalClaim.MedicalClaimDto;
import com.backend.empowerpro.dto.vacancy.VacancyCreationDto;
import com.backend.empowerpro.dto.vacancy.VacancyDto;
import com.backend.empowerpro.entity.Complaint;
import com.backend.empowerpro.entity.MedicalClaim;
import com.backend.empowerpro.repository.ComplaintRepo;
import com.backend.empowerpro.service.*;
import com.backend.empowerpro.utils.ReplyRequest;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("api/v1/hr")
@RequiredArgsConstructor
@CrossOrigin("*")
public class HrController {
    private final EventService eventService;
    private final VacancyService vacancyService;
    private final ComplaintService complaintService;
    private final LeaveService leaveService;
    private final ComplaintRepo complaintRepo;
    private final MedicalClaimService medicalClaimService;
    private final String UPLOAD_DIR_COMPLAINTS = "D:\\3rd year\\New folder\\empowerpro_backend\\uploads\\complaints";
    @Value("${project.events}")
    private String EVENTS_DIR;
    @Value("${project.claims}")
    private String CLAIMS_DIR;
    String rootPath = System.getProperty("user.dir");


    @PreAuthorize("hasAuthority('HR')")
    @PostMapping("/vacancy-creation")
    public ResponseEntity<String> creation(@RequestBody VacancyCreationDto vacancyCreationDto) {
        return ResponseEntity.ok(vacancyService.createVacancy(vacancyCreationDto));
    }
    @PreAuthorize("hasAuthority('HR')")
    @GetMapping("/vacancy-get-all")
    public ResponseEntity<List<VacancyDto>> getAllVacancies() {
        return ResponseEntity.ok(vacancyService.getAllVacancies());
    }
    @PreAuthorize("hasAuthority('HR')")
    @GetMapping("/vacancy-get-one/{id}")
    public ResponseEntity<VacancyDto> getOneVacancy(@PathVariable Long id) {
        return ResponseEntity.ok(vacancyService.getOneVacancy(id));
    }
    @PreAuthorize("hasAuthority('HR')")
    @PutMapping("/vacancy-update/{id}")
    public ResponseEntity<VacancyDto> updateVacancy(@PathVariable Long id, @RequestBody VacancyCreationDto vacancyCreationDto) {
        return ResponseEntity.ok(vacancyService.updateVacancy(id,vacancyCreationDto ));
    }
    @PreAuthorize("hasAuthority('HR')")
    @DeleteMapping ("/vacancy-delete/{id}")
    public ResponseEntity<String> deleteVacancy(@PathVariable Long id) {
        return ResponseEntity.ok(vacancyService.deleteVacancy(id));
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

    @GetMapping("/complaint-role/{role}")
    public ResponseEntity<List<ComplaintDto>> getComplaintsAssignedToRole(@PathVariable String role)
    {
        List<ComplaintDto> complaints = complaintService.getComplaintsAssignedToRole(role);
        return ResponseEntity.ok(complaints);
    }

    @PostMapping("/leave-creation")
    public ResponseEntity<String> applyLeave(@RequestBody LeaveCreationDto leaveCreationDto) {
        leaveService.saveLeave(leaveCreationDto);
        return ResponseEntity.ok("Leave applied successfully!");
    }

    @GetMapping("/leave/{userId}")
    public ResponseEntity<List<LeaveDto>> getAllLeavesByUserId(@PathVariable Long userId) {
        List<LeaveDto> leaves = leaveService.getLeavesByUser(userId);
        if (leaves.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(leaves);
    }

    @GetMapping("/leave-to-hr/{userId}")
    public ResponseEntity<List<LeaveHrDto>> getLeavesForHR(@PathVariable Long userId){
        List<LeaveHrDto> leaves = leaveService.getLeavesByAssignRole(userId);
        if(leaves.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(leaves);
    }

    @GetMapping("/leave-today")
    public ResponseEntity<List<TodayLeaveDto>> getTodayLeaves() {
        List<TodayLeaveDto> todayLeaves = leaveService.getTodayLeaves();
        return ResponseEntity.ok(todayLeaves);
    }
    @PreAuthorize("hasAuthority('HR')")
    @PostMapping(value = "/event-creation", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_OCTET_STREAM_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EventDto> createEvent(
            @RequestPart("data") @Valid EventCreationDto req,  // JSON data as part of the form
            @RequestPart("file") MultipartFile file) throws IOException {  // File upload

        if (file != null && !file.isEmpty()) {
            String filePath = rootPath + File.separator + EVENTS_DIR + file.getOriginalFilename();
            file.transferTo(new File(filePath));
            req.setImage(filePath);  // Save the file path in the event DTO
        }

        return ResponseEntity.ok(eventService.createEvent(req));
    }
    @PreAuthorize("hasAuthority('HR')")
    @GetMapping("/all-events")
    public ResponseEntity<List<EventDto>> getAllEvents(){
        return ResponseEntity.ok(eventService.getAllEvents());
    }
    @PreAuthorize("hasAuthority('HR')")
    @GetMapping("/event/{eventId}")
    public ResponseEntity<EventDto> getEvent(@PathVariable Long eventId){
        return ResponseEntity.ok(eventService.getOneEvent(eventId));
    }

    @PreAuthorize("hasAuthority('HR')")
    @GetMapping("/allmedicalClaims")
    public ResponseEntity<List<MedicalClaim>> getAllClaim(){return ResponseEntity.ok(medicalClaimService.getAllClaim());}
    @PreAuthorize("hasAuthority('HR')")
    @GetMapping("/medicalclaim/{medicalclaimId}")
    public ResponseEntity<MedicalClaim> getMedicalClaim(@PathVariable Long medicalclaimId){
        return ResponseEntity.ok(medicalClaimService.getOneClaim(medicalclaimId));
    }



    @PostMapping(value = "/claim-creation", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_OCTET_STREAM_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MedicalClaim> createClaim(
            @RequestPart("data") @Valid MedicalClaimCreation medicalClaimCreation,
            @RequestPart("file") MultipartFile file) throws IOException {
        if (file != null && !file.isEmpty()) {
            String filePath = rootPath + File.separator + CLAIMS_DIR + file.getOriginalFilename();
            file.transferTo(new File(filePath));
            medicalClaimCreation.setFileUrl(filePath);
        }
        return ResponseEntity.ok(medicalClaimService.createClaim(medicalClaimCreation));
    }


    @GetMapping("/leave-request-getOne/{leaveId}")
    public ResponseEntity<LeaveHrDto> getOneLeaveRequest(@PathVariable Long leaveId){
        return ResponseEntity.ok(leaveService.getOneRequestLeave(leaveId));
    }

    @PutMapping("/leave-set-rejected/{leaveId}")
    public ResponseEntity<?> setLeaveRejected(@PathVariable Long leaveId, @RequestParam String comment){
        System.out.println(comment);
        leaveService.setLeaveRejected(leaveId, comment);
        return ResponseEntity.ok("Leave with ID " + leaveId + " has been rejected.");
    }

    @PutMapping("/leave-set-approved/{leaveId}")
    public ResponseEntity<?> setLeaveApproved(@PathVariable Long leaveId, @RequestParam String comment){
        leaveService.setLeaveApproved(leaveId, comment);
        return ResponseEntity.ok("Leave with ID " + leaveId + " has been approved.");
    }

    @GetMapping("/leave-get-all")
    public ResponseEntity<List<LeaveDto>> getAllLeaves(){
        return ResponseEntity.ok().body(leaveService.getAllLeaves());
    }




}
