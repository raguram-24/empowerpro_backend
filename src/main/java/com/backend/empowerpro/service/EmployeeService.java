package com.backend.empowerpro.service;

import com.backend.empowerpro.auth.entity.Employee;
import com.backend.empowerpro.dto.employee.EmployeeCreationDto;
import com.backend.empowerpro.dto.employee.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    EmployeeDto getEmployeeById(Long id);
    EmployeeDto createEmployee(EmployeeCreationDto employeeCreationDto);
    EmployeeDto updateEmployee(Long id, EmployeeCreationDto employeeCreationDto);
    void deleteEmployee(Long id);
}
