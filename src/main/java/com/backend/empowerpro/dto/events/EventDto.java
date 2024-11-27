package com.backend.empowerpro.dto.events;

import com.backend.empowerpro.auth.entity.Employee;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EventDto {
    private Long id;
    private String name;
    private String reason;
    private String location;
    private LocalDate date;
    private String time;
    private Float estimatedCost;
    private String image;
    private Employee createdBy;
    private LocalDate createdAt;
    private LocalDate updatedAt;

    public EventDto(Long id, String name, String reason, String location, LocalDate date, String time, float estimatedCost, String image, Employee createdBy, LocalDate createdAt, LocalDate updatedAt) {
        this.id = id;
        this.name = name;
        this.reason = reason;
        this.location = location;
        this.date = date;
        this.time = time;
        this.estimatedCost = estimatedCost;
        this.image = image;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }




}
