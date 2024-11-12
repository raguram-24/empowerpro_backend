package com.backend.empowerpro.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
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

    private String content;
    private LocalDate date;

    // Constructors
    public Remark() {}

    public Remark(Employee reviewedActor, Employee reviewerActor, String content, LocalDate date) {
        this.reviewedActor = reviewedActor;
        this.reviewerActor = reviewerActor;
        this.content = content;
        this.date = date;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employee getReviewedActor() {
        return reviewedActor;
    }

    public void setReviewedActor(Employee reviewedActor) {
        this.reviewedActor = reviewedActor;
    }

    public Employee getReviewerActor() {
        return reviewerActor;
    }

    public void setReviewerActor(Employee reviewerActor) {
        this.reviewerActor = reviewerActor;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
