package com.backend.empowerpro.repository;

import com.backend.empowerpro.entity.Complaint;
import com.backend.empowerpro.entity.Leave;
import com.backend.empowerpro.entity.LeaveStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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

    @Query("SELECT l FROM Leave l WHERE l.employee.id != :loggedInEmployeeId")
    List<Leave> findLeavesAssignedToRole(@Param("loggedInEmployeeId") Long loggedInEmployeeId);

    @Transactional
    @Modifying
    @Query("UPDATE Leave l SET l.status = :status , l.comment = :comment WHERE l.id = :id")
    void updateLeaveStatus(@Param("id") Long id, @Param("status") LeaveStatus status,@Param("comment")String comment);

//    List<Leave> findByStatusAndDateRange(String status, LocalDate startDate);
}
