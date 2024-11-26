package com.backend.empowerpro.service;

import com.backend.empowerpro.dto.MarkCalendar.MarkCalendarDto;
import com.backend.empowerpro.entity.MarkCalendar;

import java.util.List;

public interface MarkCalendarService {
    String createMarkCalendar(MarkCalendarDto markCalendarDto);
//    List<MarkCalendar> getAllCalendarMarker();
}
