package com.backend.empowerpro.controller;


import com.backend.empowerpro.dto.accounts.AccountsCreationDto;
import com.backend.empowerpro.dto.accounts.AccountsDto;
import com.backend.empowerpro.dto.bank.BankCreationDto;
import com.backend.empowerpro.dto.complaint.ComplaintCreationDto;
import com.backend.empowerpro.dto.complaint.ComplaintDto;
import com.backend.empowerpro.dto.events.EventDto;
import com.backend.empowerpro.dto.leave.LeaveCreationDto;
import com.backend.empowerpro.dto.leave.LeaveDto;
import com.backend.empowerpro.dto.leave.TodayLeaveDto;
import com.backend.empowerpro.dto.payroll.PayRollDto;
import com.backend.empowerpro.dto.payroll.PayRollReq;
import com.backend.empowerpro.dto.payroll.PayRollViewDto;
import com.backend.empowerpro.dto.suppliers.SuppliersCreationDto;
import com.backend.empowerpro.dto.suppliers.SuppliersDto;
import com.backend.empowerpro.dto.tax.TaxCreationDto;
import com.backend.empowerpro.dto.vacancy.VacancyCreationDto;
import com.backend.empowerpro.dto.vacancy.VacancyDto;
import com.backend.empowerpro.entity.BankDetails;
import com.backend.empowerpro.entity.PayRoll;
import com.backend.empowerpro.entity.Tax;
import com.backend.empowerpro.service.*;
import com.backend.empowerpro.utils.ReplyRequest;
import lombok.RequiredArgsConstructor;
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
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/v1/finance")
@RequiredArgsConstructor

public class FinanceController {

    private final LeaveService leaveService;
    private final ComplaintService complaintService;
    private final BankService bankService;
    private final TaxService taxService;
    private final PayrollService payrollService;
    private final String UPLOAD_DIR_COMPLAINTS = "C:\\Users\\Insaf\\Desktop\\LatestEmpowerpro\\empowerpro_backend\\uploads\\complaints\\";
    private final SupplierService supplierService;
    @PreAuthorize("hasAuthority('Finance')")
    @PostMapping("/supplier-creation")
    public ResponseEntity<String> createSupplier(@RequestBody SuppliersCreationDto suppliersCreationDto) {
        return ResponseEntity.ok(supplierService.createSuppliers(suppliersCreationDto));
    }
    @PreAuthorize("hasAuthority('Finance')")
    @GetMapping("/supplier-get-all")
    public ResponseEntity<List<SuppliersDto>> getAllSuppliers() {
        return ResponseEntity.ok(supplierService.getAllSuppliers());
    }
    @PreAuthorize("hasAuthority('Finance')")
    @GetMapping("/supplier-get-one/{id}")
    public ResponseEntity<SuppliersDto> getOneSupplier(@PathVariable Long id) {
        return ResponseEntity.ok(supplierService.getOneSupplier(id));
    }
    @PreAuthorize("hasAuthority('Finance')")
    @PutMapping("/supplier-update/{id}")
    public ResponseEntity<SuppliersDto> updateSupplier(@PathVariable Long id, @RequestBody SuppliersCreationDto suppliersCreationDto) {
        return ResponseEntity.ok(supplierService.updateSuppliers(id, suppliersCreationDto));
    }
    @PreAuthorize("hasAuthority('Finance')")
    @DeleteMapping("/supplier-delete/{id}")
    public ResponseEntity<String> deleteSupplier(@PathVariable Long id) {
        return ResponseEntity.ok(supplierService.deleteSuppliers(id));
    }

//    @PreAuthorize("hasAuthority('Finance')")
    @GetMapping("/all-bankdetails")
    public ResponseEntity<List<BankDetails>> getAllDetails() {return ResponseEntity.ok(bankService.getAllDetails());}

    @PreAuthorize("hasAuthority('Finance')")
    @GetMapping("/bankdetails/{id}")
    public ResponseEntity<BankDetails> getOneDetail(@PathVariable Long eventId){
        return ResponseEntity.ok(bankService.getOneDetail(eventId));
    }

//    public final ComplaintService complaintService;
    private final AccountsService accountsService;
    @PreAuthorize("hasAuthority('Finance')")
    @PostMapping("/accounts-creation")
    public ResponseEntity<String> creationAccounts(@RequestBody AccountsCreationDto accountsCreationDto) {
        return ResponseEntity.ok(accountsService.createAccounts(accountsCreationDto));
    }
    @PreAuthorize("hasAuthority('Finance')")
    @GetMapping("/accounts-get-all")
    public ResponseEntity<List<AccountsDto>> getAllAccounts() {
        return ResponseEntity.ok(accountsService.getAllAccounts());
    }
    @PreAuthorize("hasAuthority('Finance')")
    @GetMapping("/accounts-get-one/{id}")
    public ResponseEntity<AccountsDto> getOneAccounts(@PathVariable Long id) {
        return ResponseEntity.ok(accountsService.getOneAccounts(id));
    }
    @PreAuthorize("hasAuthority('Finance')")
    @PutMapping("/accounts-update/{id}")
    public ResponseEntity<AccountsDto> updateVacancy(@PathVariable Long id, @RequestBody AccountsCreationDto accountsCreationDto) {
        return ResponseEntity.ok(accountsService.updateAccounts(id,accountsCreationDto));
    }
    @PreAuthorize("hasAuthority('Finance')")
    @DeleteMapping ("/accounts-delete/{id}")
    public ResponseEntity<String> deleteAccounts(@PathVariable Long id) {
        return ResponseEntity.ok(accountsService.deleteAccounts(id));
    }
    @PreAuthorize("hasAuthority('Finance')")
    @GetMapping("/income-get-all")
    public ResponseEntity<List<AccountsDto>> getAllIncome() {
        return ResponseEntity.ok(accountsService.getAllAccountsByIncome());
    }
    @PreAuthorize("hasAuthority('Finance')")
    @GetMapping("/expense-get-all")
    public ResponseEntity<List<AccountsDto>> getAllExpense() {
        return ResponseEntity.ok(accountsService.getAllAccountsByExpense());
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
//    @PreAuthorize("hasAuthority('Finance')")
    @PostMapping("/bank-creation")
    public ResponseEntity<BankDetails> createBankDetails(@RequestBody BankCreationDto bankCreationDto){
        return ResponseEntity.ok(bankService.createDetail(bankCreationDto));

    }

    @PreAuthorize("hasAuthority('Finance')")
    @PostMapping("/tax-creation")
    public ResponseEntity<Tax> createTax(@RequestBody TaxCreationDto taxCreationDto){
        return ResponseEntity.ok(taxService.createTax(taxCreationDto));
    }
    @PreAuthorize("hasAuthority('Finance')")
    @GetMapping("/all-tax")
    public ResponseEntity<List<Tax>> getAllTax() {return ResponseEntity.ok(taxService.getAllTax());}

    @PreAuthorize("hasAuthority('Finance')")
    @GetMapping("/tax/{id}")
    public ResponseEntity<Tax> getOneTax(@PathVariable Long id){
        return ResponseEntity.ok(taxService.getOneTax(id));
    }

    @PreAuthorize("hasAuthority('Finance')")
    @PostMapping("/slip")
    public ResponseEntity<PayRoll> createSlip(@RequestBody PayRollReq payRollReq){
        return ResponseEntity.ok(payrollService.getPayroll(payRollReq));
    }
    @PreAuthorize("hasAuthority('Finance')")
    @GetMapping("/slip-all")
    public ResponseEntity<List<PayRollViewDto>> getallslip(){
        return ResponseEntity.ok(payrollService.getAllPayRoll());
    }





}
