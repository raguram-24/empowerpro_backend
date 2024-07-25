package com.backend.empowerpro.service;

import com.backend.empowerpro.dto.*;
import com.backend.empowerpro.dto.employee.EmployeeCreationDto;
import com.backend.empowerpro.dto.employee.EmployeeDto;
import com.backend.empowerpro.dto.employee.EmployeeResponseDto;
import com.backend.empowerpro.dto.employee.EmployeeUpdateDto;
import com.backend.empowerpro.entity.Employee;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface EmployeeService {

    public List<EmployeeDto> getAllEmployees();
    public Employee createEmployee( EmployeeCreationDto employeeCreationDto);
    public EmployeeUpdateDto getOneEmployee(Long id);
    public EmployeeUpdateDto updateEmployee(Long id);
    public EmployeeUpdateDto deleteEmployee(Long id);
    public EmployeeResponseDto login( LoginDto loginDto);
}
