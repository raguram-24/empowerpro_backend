package com.backend.empowerpro.service.impl;

import com.backend.empowerpro.dto.*;
import com.backend.empowerpro.dto.employee.EmployeeCreationDto;
import com.backend.empowerpro.dto.employee.EmployeeDto;
import com.backend.empowerpro.dto.employee.EmployeeResponseDto;
import com.backend.empowerpro.dto.employee.EmployeeUpdateDto;
import com.backend.empowerpro.entity.Employee;
import com.backend.empowerpro.repository.EmployeeRepo;
import com.backend.empowerpro.service.EmployeeService;
import com.backend.empowerpro.utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImp implements EmployeeService {
    private final AuthenticationManager authenticationManager;
    private final EmployeeRepo employeeRepo;
    private final JwtService jwtService;
    private final EmployeeDetailsService employeeDetailsService;
    private final PasswordEncoder passwordEncoder;
    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    @Override
    public List<EmployeeDto> getAllEmployees() {
            List<EmployeeDto> result = new ArrayList<>();
            try {
                List<Employee> allEmployeeEntities = employeeRepo.findAll();
                for(Employee user : allEmployeeEntities){
                    EmployeeDto employeeDto = new EmployeeDto(
                            user.getId(),
                            user.getFirstName(),
                            user.getLastName(),
                            user.getAddress(),
                            user.getEmail(),
                            user.getPhoneNumber(),
                            user.getWorkTitle(),
                            user.getRole(),
                            user.getUsername(),
                            user.getSummary(),
                            user.getSkills(),
                            user.getExperiences()
                    );
                    result.add(employeeDto);
                }
            } catch (Exception e) {
                // Log the exception (if logging is set up)
                // e.g., logger.error("Error saving user", e);
                throw new RuntimeException("An unexpected error occurred while fetching users", e);
            }
            return result;
        }


    @Override
    public Employee createEmployee(EmployeeCreationDto employeeCreationDto) {
        Employee newEmployee = new Employee();
        try {

            newEmployee.setFirstName(employeeCreationDto.getFirstName());
            newEmployee.setLastName( employeeCreationDto.getLastName());
            newEmployee.setAddress( employeeCreationDto.getAddress());
            newEmployee.setEmail( employeeCreationDto.getEmail());
            newEmployee.setPhoneNumber(employeeCreationDto.getPhoneNumber());
            newEmployee.setWorkTitle(employeeCreationDto.getWorkTitle());
            newEmployee.setUsername(employeeCreationDto.getUsername());
            newEmployee.setPassword(passwordEncoder.encode(employeeCreationDto.getPassword()));
            newEmployee.setRole(employeeCreationDto.getRole());

            employeeRepo.save(newEmployee);
        } catch (Exception e) {
            // Log the exception (if logging is set up)
            // e.g., logger.error("Error saving user", e);
            throw new RuntimeException("An unexpected error occurred while fetching users", e);
        }
        return newEmployee;

    }


    @Override
    public EmployeeUpdateDto getOneEmployee(Long id) {
        return null;
    }

    @Override
    public EmployeeUpdateDto updateEmployee(Long id) {
        return null;
    }

    @Override
    public EmployeeUpdateDto deleteEmployee(Long id) {
        return null;
    }

    @Override
    public EmployeeResponseDto login(LoginDto loginDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword())
            );
            if (authentication.isAuthenticated()) {
                Optional<Employee> employee = employeeRepo.findByUsername(loginDto.getUsername());
                if (employee.isPresent()) {
                    Employee employeeDto = employee.get();
                    String token = jwtService.generateToken(employeeDetailsService.loadUserByUsername(loginDto.getUsername()));
                    logger.info("Login successful for user: {}", loginDto.getUsername());
                    return new EmployeeResponseDto(
                            employeeDto.getFirstName(),
                            employeeDto.getLastName(),
                            employeeDto.getAddress(),
                            employeeDto.getEmail(),
                            employeeDto.getPhoneNumber(),
                            employeeDto.getWorkTitle(),
                            employeeDto.getRole(),
                            loginDto.getUsername(),
                            token
                    );
                } else {
                    logger.error("Employee not found: {}", loginDto.getUsername());
                    throw new UsernameNotFoundException("Employee Not found");
                }
            } else {
                logger.error("Invalid credentials for user: {}", loginDto.getUsername());
                throw new UsernameNotFoundException("Invalid credentials");
            }
        } catch (Exception e) {
            logger.error("Error during login: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public EmployeeDto findCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        logger.info("Authentication details: {}", authentication);

        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            logger.info("Principal class: {}", principal.getClass().getName());

            String username;

            // Check if principal is a String (which is the username in this case)
            if (principal instanceof String) {
                username = (String) principal;
            } else {
                logger.error("Principal is not a String or UserDetails. Principal: {}", principal);
                return null;
            }

            // Fetch the user from the database using the username
            Optional<Employee> currentUser = employeeRepo.findByUsername(username);

            if (currentUser.isPresent()) {
                Employee emp = currentUser.get();
                EmployeeDto employeeDto = new EmployeeDto(
                        emp.getId(),
                        emp.getFirstName(),
                        emp.getLastName(),
                        emp.getAddress(),
                        emp.getEmail(),
                        emp.getPhoneNumber(),
                        emp.getWorkTitle(),
                        emp.getRole(),
                        emp.getUsername(),
                        emp.getSummary(),
                        emp.getSkills(),
                        emp.getExperiences()
                );
                logger.info("DTO Created for user: {}", username);
                return employeeDto;
            } else {
                logger.error("No user found with username: {}", username);
            }
        } else {
            logger.error("No authentication found in the security context.");
        }

        return null;
    }



}



