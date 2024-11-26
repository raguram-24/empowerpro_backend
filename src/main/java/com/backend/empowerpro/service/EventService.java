package com.backend.empowerpro.service;

import com.backend.empowerpro.dto.events.EventCreationDto;
import com.backend.empowerpro.dto.events.EventDto;
import com.backend.empowerpro.dto.events.EventUpdateDto;

public interface EventService {

    public EventDto createEvent(EventCreationDto eventCreationDto);
    public EventCreationDto updateEvent(EventUpdateDto eventUpdateDto);
    public EventCreationDto getAllEvents();
    public EventCreationDto getOneEvent(Long id);
}
