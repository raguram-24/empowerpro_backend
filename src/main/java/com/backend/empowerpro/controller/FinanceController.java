package com.backend.empowerpro.controller;

import com.backend.empowerpro.dto.suppliers.SuppliersCreationDto;
import com.backend.empowerpro.dto.suppliers.SuppliersDto;
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

}
