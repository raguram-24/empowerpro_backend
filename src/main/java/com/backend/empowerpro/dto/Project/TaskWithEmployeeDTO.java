package com.backend.empowerpro.dto.Project;

import lombok.*;

import java.sql.Time;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskWithEmployeeDTO {
    private Long id;
    private Long projectId;
    private String taskTitle;
    private String taskDescription;
    private String taskStatus;
    private Long members;
    private String dueDate;
    private String dueTime;
    private boolean dateReminder;
    private String firstName;
    private String lastName;
}