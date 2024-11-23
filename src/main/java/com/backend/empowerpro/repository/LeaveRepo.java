package com.backend.empowerpro.repository;

import com.backend.empowerpro.entity.Complaint;
import com.backend.empowerpro.entity.Leave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


public interface LeaveRepo extends JpaRepository<Leave, Long> {
    List<Leave> findByEmployee_Id(Long userId);
    @Query("SELECT l.endDate, e.id, e.firstName, e.lastName,  CAST(e.role AS string) FROM Leave l " +
            "JOIN l.employee e " +
            "WHERE l.startDate <= :today AND l.endDate >= :today")
    List<Object[]> findLeavesForToday(@Param("today") LocalDate today);
}
