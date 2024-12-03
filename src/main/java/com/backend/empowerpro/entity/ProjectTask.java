package com.backend.empowerpro.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "project_task")
public class ProjectTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @Column(name = "task_title", nullable = false)
    private String taskTitle;

    @Column(name = "task_description", nullable = false)
    private String taskDescription;

    @Column(name = "task_status", nullable = false)
    private String taskStatus;


    @ElementCollection
    @CollectionTable(name = "members", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "members")
    private List<String> members;

    @ElementCollection
    @CollectionTable(name = "labels", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "labels")
    private List<String> labels;

    @Column(name = "due_date", nullable = false)
    private Date dueDate;

    @Column(name = "due_time", nullable = false)
    private Time dueTime;

    @Column(name = "date_reminder", nullable = false)
    private String dateReminder;

    @Column(name = "url_link", nullable = false)
    private String urlLink;


    @JsonProperty("projectId")
    public Long geProjectId() {
        return project != null ? project.getProjectId(): null;
    }



}
