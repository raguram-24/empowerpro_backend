package com.backend.empowerpro.utils;

import com.backend.empowerpro.dto.events.EventCreationDto;
import com.backend.empowerpro.dto.events.EventDto;
import com.backend.empowerpro.entity.Event;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EventMapper {
    EventCreationDto toEventCreationDto (Event event);
    Event toEvent (EventCreationDto eventCreationDto);
    EventDto toEventDto (Event event);
}
