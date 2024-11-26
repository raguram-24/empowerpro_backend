package com.backend.empowerpro.dto.events;

import com.backend.empowerpro.auth.entity.Employee;
import jakarta.persistence.*;

import java.time.LocalDate;

public class EventDto {
    private Long id;
    private String name;
    private String reason;
    private String location;
    private LocalDate date;
    private String time;
    private String estimatedCost;
    private String image;
    private Employee createdBy;
    private LocalDate createdAt;
    private LocalDate updatedAt;
}
