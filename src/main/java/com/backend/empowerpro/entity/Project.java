package com.backend.empowerpro.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name="project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id" ,nullable = false)
    private  Long projectId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "project_name", nullable = false)
    private String projectName;

    @Column(name = "project_description", nullable = false)
    private String projectDescription;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(name = "client_name", nullable = false)
    private String clientName;

    @Column(name="type",nullable = false)
    private String type;

    @Column(name="stack",nullable = false)
    private String stack;

    @Column(name="teamlead",nullable = false)
    private Long teamlead;

    @ElementCollection
    @CollectionTable(name = "project_team", joinColumns = @JoinColumn(name = "project_id"))
    @Column(name = "project_team")
    private List<Long> projectTeamMembers;

    @JsonIgnore
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProjectTask> projectTasks;

    @CreationTimestamp
    @Column(name = "upload_date", updatable = false)
    private LocalDateTime uploadDate;


}
