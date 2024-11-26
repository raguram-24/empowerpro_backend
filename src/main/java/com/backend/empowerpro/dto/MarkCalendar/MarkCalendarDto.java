package com.backend.empowerpro.dto.MarkCalendar;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MarkCalendarDto {
    private Long markId;
    private Long id;
    private LocalDate eventDate;
    private LocalTime eventTime;
    private String event;
    private String state;
}
