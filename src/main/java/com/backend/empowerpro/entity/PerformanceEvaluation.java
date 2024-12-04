package com.backend.empowerpro.entity;
import com.backend.empowerpro.auth.entity.Employee;

import com.backend.empowerpro.dto.performanceevaluation.PerformanceEvaluationDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "performance_evaluations")
public class PerformanceEvaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "actor_id", nullable = false)
    private Employee actor;

//    @OneToMany(mappedBy = "reviewedActor", cascade = CascadeType.ALL)
//    @JoinColumn()
//    private List<Remark> remarks;

    @Column(name = "final_feedback")
    private String finalFeedback;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDate createdAt;

    public void setActor(Employee actor) {
        this.actor = actor;
    }

    public void setFinalFeedback(String finalFeedback) {
        this.finalFeedback = finalFeedback;
    }

    public static PerformanceEvaluationDto toPerformanceEvaluationDto(PerformanceEvaluation performanceEvaluation) {
        PerformanceEvaluationDto dto = new PerformanceEvaluationDto();
        dto.setId(performanceEvaluation.getId());
        dto.setActor(performanceEvaluation.getActor().getId());  // Get the actor's ID
        dto.setFinalFeedback(performanceEvaluation.getFinalFeedback());
        dto.setCreatedAt(performanceEvaluation.getCreatedAt());
        // Map remarks here as well if needed
        return dto;
    }

}
