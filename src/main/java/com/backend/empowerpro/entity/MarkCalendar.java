package com.backend.empowerpro.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "markCalendar")
public class MarkCalendar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "marker_id" ,nullable = false   )
    private Long markId;
    @Column(name = "user_id" ,nullable = false   )
    private Long id;
    @Column(name = "event_date", updatable = false)
    private LocalDate eventDate;
    @Column(name = "event_time", updatable = false)
    private LocalTime eventTime;
    @Column(name = "event",nullable = false   )
    private String event;
    @Column(name = "state",nullable = false   )
    private String state;

}
