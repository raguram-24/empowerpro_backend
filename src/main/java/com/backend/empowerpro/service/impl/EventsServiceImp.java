package com.backend.empowerpro.service.impl;

import com.backend.empowerpro.auth.entity.Employee;
import com.backend.empowerpro.auth.repository.EmployeeRepository;
import com.backend.empowerpro.dto.events.EventCreationDto;
import com.backend.empowerpro.dto.events.EventDto;
import com.backend.empowerpro.dto.events.EventUpdateDto;
import com.backend.empowerpro.entity.Accounts;
import com.backend.empowerpro.entity.Event;
import com.backend.empowerpro.repository.EventRepo;
import com.backend.empowerpro.service.EventService;
import com.backend.empowerpro.utils.EventMapper;
import com.backend.empowerpro.utils.LoggedInEmployee;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventsServiceImp implements EventService {
    private final EventRepo eventRepo;
    private final EventMapper eventMapper;
    private static final Logger logger = LoggerFactory.getLogger(EventsServiceImp.class);
    private final EmployeeRepository employeeRepository;
    @Override
    public EventDto createEvent(EventCreationDto eventCreationDto) {
        try{
            String username = LoggedInEmployee.getLoggedInUsername();
            var employee = employeeRepository
                    .findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("Employee not found!"));

            Event newEvent = eventMapper.toEvent(eventCreationDto);
            newEvent.setCreatedBy(employee);
            eventRepo.save(newEvent);
            logger.info("New Event Created with" + newEvent);
            return new EventDto(newEvent.getId(),
                    newEvent.getName(),
                    newEvent.getReason(),
                    newEvent.getLocation(),
                    newEvent.getDate(),
                    newEvent.getTime(),
                    newEvent.getEstimatedCost(),
                    newEvent.getImage(),
                    employee,
                    newEvent.getCreatedAt(),
                    newEvent.getUpdatedAt());
        }catch (Exception e){
            logger.error("Error Has been Occurred {}",e.getMessage());
            throw new RuntimeException("An unexpected error occurred while fetching Accounts", e);
        }
    }

    @Override
    public EventCreationDto updateEvent(EventUpdateDto eventUpdateDto) {
        return null;
    }

    @Override
    public EventCreationDto getAllEvents() {
        return null;
    }

    @Override
    public EventCreationDto getOneEvent(Long id) {
        return null;
    }
}
