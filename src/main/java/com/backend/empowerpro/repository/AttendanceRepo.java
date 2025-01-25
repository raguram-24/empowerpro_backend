package com.backend.empowerpro.repository;

import com.backend.empowerpro.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface AttendanceRepo extends JpaRepository<Attendance, Long> {
    List<Attendance> findAttendanceByUserId(long id);
    Attendance findAttendanceById(Long id);

    List<Attendance> findAttendanceByDateAndUserId(LocalDate date ,Long UserId);

    Attendance findAttendanceByDateAndId(LocalDate date ,Long Id);

}
