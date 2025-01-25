package com.backend.empowerpro.service;


import com.backend.empowerpro.dto.attendance.CheckoutAttendanceDto;
import com.backend.empowerpro.dto.attendance.CreateAttendanceDto;
import com.backend.empowerpro.dto.attendance.SearchDateRangeDto;
import com.backend.empowerpro.entity.Attendance;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface AttendanceService {

    Attendance createAttendance(Long UserId);
//
//    Attendance checkoutUpdateAttendance(CheckoutAttendanceDto checkoutAttendanceDto,Long id);
//
//    List<Attendance> getAllAttendance();

    String updateCheckout(Long id);

    String updateBreakTime(Long id);

    String addContinueTime(Long id);

    List<Attendance> getAllAttendanceByUserId(Long userId);

    Attendance getAttendanceById(Long id);

    List<Attendance> getAttendanceByDateRange(SearchDateRangeDto searchDateRangeDto);

    List<Attendance> getAttendanceByDate(Long UserId, LocalDate date);

    Time getWorkingHours(Long Id, LocalDate date);
}
