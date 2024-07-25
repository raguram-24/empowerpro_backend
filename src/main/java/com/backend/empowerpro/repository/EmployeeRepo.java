package com.backend.empowerpro.repository;

import com.backend.empowerpro.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepo extends JpaRepository<Employee,Long> {
    Optional<Employee> findByUsername(String username);
}
