package com.backend.empowerpro.service;

import com.backend.empowerpro.dto.attendance.CreateAttendanceDto;
import com.backend.empowerpro.entity.Attendance;

import java.util.Date;
import java.util.List;

public interface AttendanceService {
    Attendance createAttendance(Long userId);
    String updateCheckout(Long attendanceId);

    String updateBreakTime(Long attendanceId);

    String addContinueTime(Long attendanceId);

    List<Attendance> getAllAttendanceByUserId(Long userId);


    Attendance getAttendanceById(Long attendanceId);
}
