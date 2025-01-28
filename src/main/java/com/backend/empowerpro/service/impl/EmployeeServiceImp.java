package com.backend.empowerpro.service.impl;

import com.backend.empowerpro.auth.entity.Employee;
import com.backend.empowerpro.auth.entity.EmployeeRole;
import com.backend.empowerpro.auth.repository.EmployeeRepository;
import com.backend.empowerpro.dto.employee.EmployeeCreationDto;
import com.backend.empowerpro.dto.employee.EmployeeDto;
import com.backend.empowerpro.exception.EmployeeNotFoundException;
import com.backend.empowerpro.service.EmployeeService;
import com.backend.empowerpro.utils.EmployeeMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImp implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImp.class);

    @Override
    public List<Employee> getAllEmployees(String role) {
        System.out.println("ok");
        return  employeeRepository.findEmployeeByRole(EmployeeRole.valueOf(role));
//        return employeeRepository.findAll();
    }

    @Override
    public EmployeeDto getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with ID: " + id));
        return employeeMapper.toEmployeeDto(employee);
    }



    @Override
    public EmployeeDto createEmployee(EmployeeCreationDto employeeCreationDto) {
        Employee employee = employeeMapper.toEmployee(employeeCreationDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return employeeMapper.toEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto updateEmployee(Long id, EmployeeCreationDto employeeCreationDto) {
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with ID: " + id));
        employeeMapper.updateEmployeeFromDto(employeeCreationDto, existingEmployee);
        Employee updatedEmployee = employeeRepository.save(existingEmployee);
        return employeeMapper.toEmployeeDto(updatedEmployee);
    }

    @Override
    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with ID: " + id));
        employeeRepository.delete(employee);
    }

}
