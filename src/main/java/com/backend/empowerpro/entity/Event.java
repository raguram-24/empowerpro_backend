package com.backend.empowerpro.entity;

import com.backend.empowerpro.auth.entity.Employee;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "events")

public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id" ,nullable = false   )
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String reason;
    @Column(nullable = false)
    private String location;
    @Column(nullable = false)
    private LocalDate date;
    @Column(nullable = false)
    private String time;
    @Column(nullable = false)
    private String estimatedCost;
    @Column(nullable = false)
    private String image;
    @Column(nullable = false)
    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private Employee createdBy;
    private LocalDate createdAt;
    private LocalDate updatedAt;
}
