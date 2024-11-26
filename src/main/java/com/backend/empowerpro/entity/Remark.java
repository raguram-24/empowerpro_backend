package com.backend.empowerpro.entity;
import com.backend.empowerpro.auth.entity.Employee;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "remarks")
public class Remark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "reviewed_actor_id", nullable = false)
    private Employee reviewedActor;

    @ManyToOne
    @JoinColumn(name = "reviewer_actor_id", nullable = false)
    private Employee reviewerActor;

    @NotNull
    @Column(name = "content", nullable = false, length = 500)
    private String content;

    @CreationTimestamp
    @Column(name = "date", nullable = false, updatable = false)
    private LocalDate date;
}

