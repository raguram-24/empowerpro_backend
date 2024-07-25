package com.backend.empowerpro.service.impl;

import com.backend.empowerpro.entity.Employee;
import com.backend.empowerpro.repository.EmployeeRepo;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeDetailsService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeDetailsService.class);

    @Autowired
    private final EmployeeRepo employeeRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("Loading user by username: {}", username);
        try {
            Employee employee = employeeRepo.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            logger.info("User found: {}", username);
            return User.builder()
                    .username(employee.getUsername())
                    .password(employee.getPassword())
                    .roles(employee.getRole())
                    .build();
        } catch (UsernameNotFoundException e) {
            logger.error("User not found: {}", username);
            throw e;
        } catch (Exception e) {
            logger.error("Error occurred while loading user by username: {}", username, e);
            throw new UsernameNotFoundException("An error occurred while loading the user", e);
        }
    }
}
