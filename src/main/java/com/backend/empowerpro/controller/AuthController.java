package com.backend.empowerpro.controller;


import com.backend.empowerpro.dto.LoginDto;
import com.backend.empowerpro.dto.employee.EmployeeCreationDto;
import com.backend.empowerpro.dto.employee.EmployeeResponseDto;
import com.backend.empowerpro.entity.Employee;
import com.backend.empowerpro.service.impl.EmployeeServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final EmployeeServiceImp employeeServiceImp;
    @PostMapping("/login")
    public ResponseEntity<EmployeeResponseDto> login(@RequestBody LoginDto loginDto) {
        return ResponseEntity.ok(employeeServiceImp.login(loginDto));
    }
}
