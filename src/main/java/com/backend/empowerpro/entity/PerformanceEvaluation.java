package com.backend.empowerpro.entity;
import com.backend.empowerpro.auth.entity.Employee;

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

    @OneToOne
    @JoinColumn(name = "actor_id", nullable = false)
    private Employee actor;

    @OneToMany(mappedBy = "reviewedActor", cascade = CascadeType.ALL)
    private List<Remark> remarks;

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

}
