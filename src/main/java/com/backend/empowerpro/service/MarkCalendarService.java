package com.backend.empowerpro.service;

import com.backend.empowerpro.dto.MarkCalendar.MarkCalendarDto;
import com.backend.empowerpro.entity.MarkCalendar;

import java.util.List;

public interface MarkCalendarService {
    List<MarkCalendar> getAllCalendarMarker();

    public String createMarkCalendar(MarkCalendarDto markCalendarDto );


    MarkCalendar getMarkerById(Long MarkId) throws Throwable;

    //     delete
    String deleteMarker(Long id);

    //update calendar
    MarkCalendarDto updateState(Long id, MarkCalendarDto markCalendarDto);
}
