package com.backend.empowerpro.entity;
import com.backend.empowerpro.auth.entity.Employee;

import com.backend.empowerpro.dto.performanceevaluation.PerformanceEvaluationDto;
import com.backend.empowerpro.dto.remark.RemarkDto;
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

    public static RemarkDto toRemarkDto(Remark remark) {
        RemarkDto dto = new RemarkDto();
        dto.setId(remark.getId());
        dto.setReviewedActorId(remark.getReviewedActor().getId());
        dto.setReviewerActorId(remark.getReviewerActor().getId());
        dto.setContent(remark.getContent());
        dto.setDate(remark.getDate());
        // Map remarks here as well if needed
        return dto;
    }
}

