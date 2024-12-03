package com.backend.empowerpro.service;


import com.backend.empowerpro.dto.attendance.CheckoutAttendanceDto;
import com.backend.empowerpro.dto.attendance.CreateAttendanceDto;
import com.backend.empowerpro.entity.Attendance;

import java.util.List;

public interface AttendanceService {

    Attendance createAttendance(CreateAttendanceDto createAttendanceDto);

    Attendance checkoutUpdateAttendance(CheckoutAttendanceDto checkoutAttendanceDto,Long id);

    List<Attendance> getAllAttendance();
}
