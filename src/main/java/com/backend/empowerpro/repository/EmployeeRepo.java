package com.backend.empowerpro.repository;

import com.backend.empowerpro.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {
    Employee findByUsername(String username);

    Employee findByEmail(String email);
}
