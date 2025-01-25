package com.backend.empowerpro.service.impl;

import com.backend.empowerpro.dto.attendance.CheckoutAttendanceDto;
import com.backend.empowerpro.dto.attendance.CreateAttendanceDto;
import com.backend.empowerpro.entity.Attendance;
import com.backend.empowerpro.repository.AttendanceRepo;
import com.backend.empowerpro.service.AttendanceService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendanceServiceImp implements AttendanceService {
    private final AttendanceRepo attendanceRepo;


    @Override
    public Attendance createAttendance(CreateAttendanceDto createAttendanceDto) {
       Attendance attendance = new Attendance();
       attendance.setDate(createAttendanceDto.getDate());
       attendance.setCheckIn(createAttendanceDto.getCheckIn());
       attendance.setUserId(createAttendanceDto.getUserId());
       Attendance  createAttendance    =attendanceRepo.save(attendance);
        return createAttendance;
    }

    @Override
    public Attendance checkoutUpdateAttendance(CheckoutAttendanceDto checkoutAttendanceDto,Long id) {
        Attendance attendance = attendanceRepo.findAttendanceByUserId(12345);
       System.out.println("hi");
        attendance.setCheckOut(checkoutAttendanceDto.getCheckOut());
        return attendanceRepo.save(attendance);
    }

    @Override
    public List<Attendance> getAllAttendance() {
        return attendanceRepo.findAll();
    }
    //gdgdf

}
