package com.backend.empowerpro.dto.Project;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProjectTaskDisplayDto {
    private String projectName; // Add this field
    private Long projectId;
    private Long id;
    private String taskTitle;
    private String taskDescription;
    private Long members;
    private String taskStatus;
    private LocalDate dueDate;
    private Time dueTime;
    private String dateReminder;

    public ProjectTaskDisplayDto(Long id, Long projectId, String projectName, String taskTitle, String taskDescription, String taskStatus, Long members, LocalDate dueDate, Time dueTime, String dateReminder) {
        this.projectName = projectName;
        this.id = id;
        this.projectId = projectId;
        this.taskTitle = taskTitle;
        this.taskDescription = taskDescription;
        this.members = members;
        this.taskStatus = taskStatus;
        this.dueDate = dueDate;
        this.dueTime = dueTime;
        this.dateReminder = dateReminder;
    }

}
