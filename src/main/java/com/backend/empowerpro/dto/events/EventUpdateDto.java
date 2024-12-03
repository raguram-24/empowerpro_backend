package com.backend.empowerpro.dto.events;

import java.time.LocalDate;

public class EventUpdateDto {
    private String name;
    private String reason;
    private String location;
    private LocalDate date;
    private String time;
    private Float estimatedCost;
    private String image;
}
