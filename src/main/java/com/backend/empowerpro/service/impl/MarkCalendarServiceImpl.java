package com.backend.empowerpro.service.impl;

import com.backend.empowerpro.dto.MarkCalendar.MarkCalendarDto;
import com.backend.empowerpro.entity.MarkCalendar;
import com.backend.empowerpro.repository.MarkCalendarRepo;
import com.backend.empowerpro.service.MarkCalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MarkCalendarServiceImpl implements MarkCalendarService {
    private final MarkCalendarRepo markCalendarRepo;

//    @Override
//    public List<MarkCalendar> getAllCalendarMarker() {
//        List<MarkCalendar> markCalendars= markCalendarRepo.findAll();
//        return markCalendars.stream().map((markCalendar)-> MarkCalendarMapper.mapToMarkCalendar(markCalendar))
//                .collect(Collectors.toList());
//    }

    @Override
    public String createMarkCalendar(MarkCalendarDto markCalendarDto) {
        MarkCalendar newMarkCalendar = new MarkCalendar();

        try {
            newMarkCalendar.setMarkId(markCalendarDto.getMarkId());
            newMarkCalendar.setId(markCalendarDto.getId());
            newMarkCalendar.setEventDate(markCalendarDto.getEventDate());
            newMarkCalendar.setEventTime(markCalendarDto.getEventTime());
            newMarkCalendar.setEvent(markCalendarDto.getEvent());
            newMarkCalendar.setState(markCalendarDto.getState());

            markCalendarRepo.save(newMarkCalendar);
            return "Mark Calendar created successfully with ID: " + newMarkCalendar.getId();
        } catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred while creating mark calendar", e);
        }
    }
}
