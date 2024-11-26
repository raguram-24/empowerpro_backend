package com.backend.empowerpro.entity;

import com.backend.empowerpro.auth.entity.Employee;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;

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

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private Employee createdBy;
    @CreationTimestamp
    private LocalDate createdAt;
    @UpdateTimestamp
    private LocalDate updatedAt;
}
