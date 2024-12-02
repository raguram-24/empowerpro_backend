package com.backend.empowerpro.service;

import com.backend.empowerpro.dto.employee.EmployeeCreationDto;
import com.backend.empowerpro.dto.employee.EmployeeDto;
import com.backend.empowerpro.dto.employee.EmployeeUpdateRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface EmployeeService {
    List<EmployeeDto> getAllEmployees();
    EmployeeDto getOneEmployee(Long id);
    EmployeeDto addEmployee(EmployeeCreationDto employeeCreationDto, MultipartFile profile) throws IOException;
    EmployeeDto updateEmployee(Long id, EmployeeUpdateRequest employeeUpdateRequest, MultipartFile profile) throws IOException;
    void deleteEmployee(Long id);
}
