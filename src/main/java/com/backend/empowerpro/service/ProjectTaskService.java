package com.backend.empowerpro.service;

import com.backend.empowerpro.dto.Project.ProjectTaskCreationDto;
import com.backend.empowerpro.entity.ProjectTask;

import java.util.List;

public interface ProjectTaskService {
    ProjectTask createProjectTask(ProjectTaskCreationDto projectTaskCreationDto);

    List<ProjectTask> getProjectTaskByProjectId(Long projectId);

    List<ProjectTask> getProjectTaskByUserId(Long userId);

    List<ProjectTask> getProjectTaskById(Long projectTaskId);

    String deleteProjectTask(Long projectTaskId);

    ProjectTask updateProjectTask(Long projectTaskId);

}
