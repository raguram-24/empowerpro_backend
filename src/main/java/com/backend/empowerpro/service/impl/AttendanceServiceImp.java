package com.backend.empowerpro.service.impl;

import com.backend.empowerpro.dto.attendance.CheckoutAttendanceDto;
import com.backend.empowerpro.dto.attendance.CreateAttendanceDto;
import com.backend.empowerpro.dto.attendance.SearchDateRangeDto;
import com.backend.empowerpro.entity.Attendance;
import com.backend.empowerpro.repository.AttendanceRepo;
import com.backend.empowerpro.service.AttendanceService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendanceServiceImp implements AttendanceService {
    private final AttendanceRepo attendanceRepo;


    @Override
    public Attendance createAttendance(Long UserId) {
       Attendance attendance = new Attendance();
       attendance.setDate(LocalDate.now());
        attendance.setCheckIn(Time.valueOf(LocalTime.now()));
        attendance.setUserId(UserId);
       Attendance  createAttendance    =attendanceRepo.save(attendance);
        return createAttendance;
    }

//    @Override
//    public Attendance checkoutUpdateAttendance(CheckoutAttendanceDto checkoutAttendanceDto,Long id) {
//        Attendance attendance = attendanceRepo.findAttendanceByUserId(12345);
//       System.out.println("hi");
//        attendance.setCheckOut(checkoutAttendanceDto.getCheckOut());
//        return attendanceRepo.save(attendance);
//    }
//
//    @Override
//    public List<Attendance> getAllAttendance() {
//        return attendanceRepo.findAll();
//    }

    @Override
    public String updateCheckout(Long id) {
       Attendance updateAttendance = attendanceRepo.findAttendanceById(id);
       updateAttendance.setCheckOut(Time.valueOf(LocalTime.now()));
       Attendance updatedAttendance  = attendanceRepo.save(updateAttendance);
       return  "Successfully Update Checkout time";
    }

    @Override
    public String updateBreakTime(Long id) {
        Attendance updateAttendance = attendanceRepo.findAttendanceById(id);
        if (updateAttendance.getBreak_time() == null) {
            updateAttendance.setBreak_time(new ArrayList<>());
        }
        updateAttendance.getBreak_time().add(Time.valueOf(LocalTime.now()));

        attendanceRepo.save(updateAttendance);
        return "Updated Successfully";
    }

    @Override
    public String addContinueTime(Long id) {
        return null;
    }

    @Override
    public List<Attendance> getAllAttendanceByUserId(Long userId) {
        System.out.println("Done");
        return attendanceRepo.findAttendanceByUserId(userId);
    }

    @Override
    public Attendance getAttendanceById(Long id) {
        return attendanceRepo.findAttendanceById(id);
    }

    @Override
    public List<Attendance> getAttendanceByDateRange(SearchDateRangeDto searchDateRangeDto) {
        return null;
    }

    @Override
    public List<Attendance> getAttendanceByDate(Long UserId, LocalDate date) {
        return attendanceRepo.findAttendanceByDateAndUserId(date,UserId);
    }

    @Override
    public Time getWorkingHours(Long Id, LocalDate date) {
        Attendance attendance = attendanceRepo.findAttendanceByDateAndId(date,Id);
        return null;
    }

}
