package com.backend.empowerpro.controller;


import com.backend.empowerpro.dto.accounts.AccountsCreationDto;
import com.backend.empowerpro.dto.accounts.AccountsDto;
import com.backend.empowerpro.dto.complaint.ComplaintDto;
import com.backend.empowerpro.dto.suppliers.SuppliersCreationDto;
import com.backend.empowerpro.dto.suppliers.SuppliersDto;
import com.backend.empowerpro.dto.vacancy.VacancyCreationDto;
import com.backend.empowerpro.dto.vacancy.VacancyDto;
import com.backend.empowerpro.service.AccountsService;
import com.backend.empowerpro.service.ComplaintService;
import com.backend.empowerpro.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/finance")
@RequiredArgsConstructor

public class FinanceController {
    private final SupplierService supplierService;

    @PostMapping("/supplier-creation")
    public ResponseEntity<String> createSupplier(@RequestBody SuppliersCreationDto suppliersCreationDto) {
        return ResponseEntity.ok(supplierService.createSuppliers(suppliersCreationDto));
    }

    @GetMapping("/supplier-get-all")
    public ResponseEntity<List<SuppliersDto>> getAllSuppliers() {
        return ResponseEntity.ok(supplierService.getAllSuppliers());
    }

    @GetMapping("/supplier-get-one/{id}")
    public ResponseEntity<SuppliersDto> getOneSupplier(@PathVariable Long id) {
        return ResponseEntity.ok(supplierService.getOneSupplier(id));
    }

    @PutMapping("/supplier-update/{id}")
    public ResponseEntity<SuppliersDto> updateSupplier(@PathVariable Long id, @RequestBody SuppliersCreationDto suppliersCreationDto) {
        return ResponseEntity.ok(supplierService.updateSuppliers(id, suppliersCreationDto));
    }

    @DeleteMapping("/supplier-delete/{id}")
    public ResponseEntity<String> deleteSupplier(@PathVariable Long id) {
        return ResponseEntity.ok(supplierService.deleteSuppliers(id));
    }
//    public final ComplaintService complaintService;
    private final AccountsService accountsService;
    @PostMapping("/accounts-creation")
    public ResponseEntity<String> creationAccounts(@RequestBody AccountsCreationDto accountsCreationDto) {
        return ResponseEntity.ok(accountsService.createAccounts(accountsCreationDto));
    }
    @GetMapping("/accounts-get-all")
    public ResponseEntity<List<AccountsDto>> getAllAccounts() {
        return ResponseEntity.ok(accountsService.getAllAccounts());
    }

    @GetMapping("/accounts-get-one/{id}")
    public ResponseEntity<AccountsDto> getOneAccounts(@PathVariable Long id) {
        return ResponseEntity.ok(accountsService.getOneAccounts(id));
    }

    @PutMapping("/accounts-update/{id}")
    public ResponseEntity<AccountsDto> updateVacancy(@PathVariable Long id, @RequestBody AccountsCreationDto accountsCreationDto) {
        return ResponseEntity.ok(accountsService.updateAccounts(id,accountsCreationDto));
    }

    @DeleteMapping ("/accounts-delete/{id}")
    public ResponseEntity<String> deleteAccounts(@PathVariable Long id) {
        return ResponseEntity.ok(accountsService.deleteAccounts(id));
    }

    @GetMapping("/income-get-all")
    public ResponseEntity<List<AccountsDto>> getAllIncome() {
        return ResponseEntity.ok(accountsService.getAllAccountsByIncome());
    }

    @GetMapping("/expense-get-all")
    public ResponseEntity<List<AccountsDto>> getAllExpense() {
        return ResponseEntity.ok(accountsService.getAllAccountsByExpense());
    }

//    @PostMapping("/complaint-creation")
//    public ResponseEntity<String> creation(@RequestBody ComplaintDto complaintDto) {
//        return ResponseEntity.ok(complaintService.createComplaint(complaintDto));
//    }
//
//    @GetMapping("/complaint-FromMyself")
//    public ResponseEntity<List<ComplaintDto>> getComplaintsFromMyself() {
//        return ResponseEntity.ok(complaintService.getComplaintsFromMyself());
//    }
}
