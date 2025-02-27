package com.backend.empowerpro.auth.repository;

import com.backend.empowerpro.auth.entity.Employee;
import com.backend.empowerpro.auth.entity.EmployeeRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByUsername(String username);

    List<Employee> findEmployeeByRole(EmployeeRole role);
}
