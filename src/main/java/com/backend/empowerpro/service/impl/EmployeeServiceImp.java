package com.backend.empowerpro.service.impl;

import com.backend.empowerpro.auth.entity.Employee;
import com.backend.empowerpro.auth.repository.EmployeeRepository;
import com.backend.empowerpro.auth.utils.EmployeeResponse;
import com.backend.empowerpro.auth.utils.EmployeeUpdateRequest;
import com.backend.empowerpro.exception.EmployeeNotFoundException;
import com.backend.empowerpro.service.EmployeeService;
import com.backend.empowerpro.service.FileService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImp implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final FileService fileService;

    @Value("${project.employeeProfile}")
    private String path;

    @Value("${base.url}")
    private String baseUrl;

    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    @Override
    public List<EmployeeResponse> getAllEmployees() {
        return employeeRepository.findAll().stream().map(employee -> {
            String posterUrl = baseUrl + "/file/profile/" + employee.getProfile();
            return new EmployeeResponse(
                    employee.getId(),
                    employee.getFirstName(),
                    employee.getLastName(),
                    employee.getAddress(),
                    employee.getEmail(),
                    employee.getUsername(),
                    employee.getWorkTitle(),
                    employee.getRole(),
                    employee.getSummary(),
                    employee.getSkills(),
                    employee.getExperiences(),
                    employee.getProfile(),
                    posterUrl
            );
        }).collect(Collectors.toList());
    }

    @Override
    public EmployeeResponse getOneEmployee(Long id) {
        try {
            Employee employee = employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException("Employee Not Found"));
            String posterUrl = baseUrl + "/file/profile/" + employee.getProfile();
            return new EmployeeResponse(
                    employee.getId(),
                    employee.getFirstName(),
                    employee.getLastName(),
                    employee.getAddress(),
                    employee.getEmail(),
                    employee.getUsername(),
                    employee.getWorkTitle(),
                    employee.getRole(),
                    employee.getSummary(),
                    employee.getSkills(),
                    employee.getExperiences(),
                    employee.getProfile(),
                    posterUrl
            );
        } catch (Exception e) {
            logger.error("An unexpected error occurred while fetching Employee: {}", e.getMessage(), e);
            throw new RuntimeException("An unexpected error occurred while fetching employee", e);
        }
    }

    @Override
    public EmployeeResponse updateEmployee(Long id, EmployeeUpdateRequest employeeUpdateRequest, MultipartFile file) throws IOException {
        Employee foundEmployee = employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException("Employee Not Found"));

        String fileName = foundEmployee.getProfile();
        if (file != null && !file.isEmpty()) {
            // Delete old file if exists
            Files.deleteIfExists(Paths.get(path + File.separator + fileName));
            fileName = fileService.uploadFile(path, file);
        }
        foundEmployee.setProfile(fileName);
        foundEmployee.setFirstName(employeeUpdateRequest.getFirstName());
        foundEmployee.setLastName(employeeUpdateRequest.getLastName());
        foundEmployee.setAddress(employeeUpdateRequest.getAddress());
        foundEmployee.setEmail(employeeUpdateRequest.getEmail());
        foundEmployee.setPhoneNumber(employeeUpdateRequest.getPhoneNumber());
        foundEmployee.setWorkTitle(employeeUpdateRequest.getWorkTitle());
        foundEmployee.setRole(employeeUpdateRequest.getRole());
        foundEmployee.setUsername(employeeUpdateRequest.getUsername());
        foundEmployee.setExperiences(employeeUpdateRequest.getExperiences());
        foundEmployee.setSummary(employeeUpdateRequest.getSummary());
        foundEmployee.setSkills(employeeUpdateRequest.getSkills());

        Employee updatedEmployee = employeeRepository.save(foundEmployee);

        String profileUrl = baseUrl + "/file/profile/" + updatedEmployee.getProfile();
        return new EmployeeResponse(
                updatedEmployee.getId(),
                updatedEmployee.getFirstName(),
                updatedEmployee.getLastName(),
                updatedEmployee.getAddress(),
                updatedEmployee.getEmail(),
                updatedEmployee.getUsername(),
                updatedEmployee.getWorkTitle(),
                updatedEmployee.getRole(),
                updatedEmployee.getSummary(),
                updatedEmployee.getSkills(),
                updatedEmployee.getExperiences(),
                updatedEmployee.getProfile(),
                profileUrl
        );
    }

    @Override
    public EmployeeResponse deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException("Employee Not Found"));
        employeeRepository.delete(employee);
        return new EmployeeResponse(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getAddress(),
                employee.getEmail(),
                employee.getUsername(),
                employee.getWorkTitle(),
                employee.getRole(),
                employee.getSummary(),
                employee.getSkills(),
                employee.getExperiences(),
                employee.getProfile(),
                null
        );
    }
}
