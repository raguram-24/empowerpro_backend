package com.backend.empowerpro.service.impl;

import com.backend.empowerpro.entity.Attendance;
import com.backend.empowerpro.repository.AttendanceRepo;
import com.backend.empowerpro.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendanceServiceImp implements AttendanceService {
    private final AttendanceRepo attendanceRepo;

    @Override
    public Attendance createAttendance(Long userId) {
        Attendance attendance = new Attendance();
        ZonedDateTime now = ZonedDateTime.now(ZoneId.systemDefault());

        attendance.setUserId(userId);
        attendance.setDate(now.toLocalDate());
        attendance.setCheckIn(Time.valueOf(now.toLocalTime()));

        Attendance created = attendanceRepo.save(attendance);

        return created;
    }


    @Override
    public String updateCheckout(Long attendanceId) {
        Attendance updateAttendance = attendanceRepo.findAttendanceByAttendanceId(attendanceId);
        ZonedDateTime now = ZonedDateTime.now(ZoneId.systemDefault());
        updateAttendance.setCheckOut(Time.valueOf(now.toLocalTime()));
         attendanceRepo.save(updateAttendance);
         return "Successfully update checkout";
    }


    @Override
    public String updateBreakTime(Long attendanceId) {
        // Fetch attendance record by ID
        Attendance updateAttendance = attendanceRepo.findAttendanceByAttendanceId(attendanceId);
        if (updateAttendance == null) {
            throw new IllegalArgumentException("Attendance not found for ID: " + attendanceId);
        }

        ZonedDateTime now = ZonedDateTime.now(ZoneId.systemDefault());
        Time breakTimeEntry = Time.valueOf(now.toLocalTime());
        if (updateAttendance.getBreakTime() == null) {
            updateAttendance.setBreakTime(new ArrayList<>());
        }
        updateAttendance.getBreakTime().add(breakTimeEntry);
        attendanceRepo.save(updateAttendance);

        return "Break time added successfully!";
    }

    @Override
    public String addContinueTime(Long attendanceId) {
        Attendance updateAttendance = attendanceRepo.findAttendanceByAttendanceId(attendanceId);
        if (updateAttendance == null) {
            throw new IllegalArgumentException("Attendance not found for ID: " + attendanceId);
        }
        ZonedDateTime now = ZonedDateTime.now(ZoneId.systemDefault());
        Time ContinueTimeEntry = Time.valueOf(now.toLocalTime());
        if (updateAttendance.getContinueTime() == null) {
            updateAttendance.setContinueTime(new ArrayList<>());
        }
        updateAttendance.getContinueTime().add(ContinueTimeEntry);
        attendanceRepo.save(updateAttendance);

        return "Continue time added successfully!";
    }

    @Override
    public List<Attendance> getAllAttendanceByUserId(Long userId) {
        return attendanceRepo.findAttendanceByUserId(userId);
    }

    @Override
    public Attendance getAttendanceById(Long attendanceId) {
        return attendanceRepo.findAttendanceByAttendanceId(attendanceId);
    }

}
