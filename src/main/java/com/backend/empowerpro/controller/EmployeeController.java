package com.backend.empowerpro.controller;


import com.backend.empowerpro.dto.employee.EmployeeCreationDto;
import com.backend.empowerpro.entity.Employee;
import com.backend.empowerpro.service.impl.EmployeeServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeServiceImp employeeServiceImp;
    @PostMapping("/creation")
    public ResponseEntity<Employee> creation(@RequestBody EmployeeCreationDto employeeCreationDto) {
        return ResponseEntity.ok(employeeServiceImp.createEmployee(employeeCreationDto));
    }
}
