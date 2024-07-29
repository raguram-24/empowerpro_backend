package com.backend.empowerpro.controller;


import com.backend.empowerpro.dto.accounts.AccountsCreationDto;
import com.backend.empowerpro.dto.accounts.AccountsDto;
import com.backend.empowerpro.dto.vacancy.VacancyCreationDto;
import com.backend.empowerpro.dto.vacancy.VacancyDto;
import com.backend.empowerpro.service.AccountsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/finance")
@RequiredArgsConstructor
public class FinanceController {
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
}
