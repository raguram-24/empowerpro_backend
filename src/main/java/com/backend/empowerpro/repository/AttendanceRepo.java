package com.backend.empowerpro.repository;

import com.backend.empowerpro.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceRepo extends JpaRepository<Attendance, Long> {
    Attendance findAttendanceByUserId(long id);

}
