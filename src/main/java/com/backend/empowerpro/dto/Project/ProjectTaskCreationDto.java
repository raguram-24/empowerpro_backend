package com.backend.empowerpro.dto.Project;

import com.backend.empowerpro.entity.Project;
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
public class ProjectTaskCreationDto {
    private Project project;
    private String taskTitle;
    private String taskDescription;
    private List<String> members;
    private List<String> labels;
    private String taskStatus;
    private Date dueDate;
    private Time dueTime;
    private String dateReminder;
    private String urlLink;
}
