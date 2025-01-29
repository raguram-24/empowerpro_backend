package com.backend.empowerpro.dto.Project;

import com.backend.empowerpro.entity.Project;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProjectTaskCreationDto {
    private Long projectId;
    private String taskTitle;
    private String taskDescription;
    private Long members;
    private String taskStatus;
    private LocalDate dueDate;
    private Time dueTime;
    private String dateReminder;
}
