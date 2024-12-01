package com.backend.empowerpro.repository;

import com.backend.empowerpro.entity.LeaveBalance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LeaveBalanceRepo extends JpaRepository<LeaveBalance, Long> {
    Optional<LeaveBalance> findByEmployee_Id(Long userId);
}
