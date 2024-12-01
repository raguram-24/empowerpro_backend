package com.backend.empowerpro.service;

import com.backend.empowerpro.dto.events.EventCreationDto;
import com.backend.empowerpro.dto.events.EventDto;
import com.backend.empowerpro.dto.events.EventUpdateDto;

import java.util.List;

public interface EventService {

    public EventDto createEvent(EventCreationDto eventCreationDto);
    public EventDto updateEvent(EventUpdateDto eventUpdateDto, Long id);
    public List<EventDto> getAllEvents();
    public EventDto getOneEvent(Long id);
}
