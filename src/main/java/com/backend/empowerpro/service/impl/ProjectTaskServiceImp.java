package com.backend.empowerpro.service.impl;

import com.backend.empowerpro.dto.Project.ProjectTaskCreationDto;
import com.backend.empowerpro.entity.AddFavouriteBlog;
import com.backend.empowerpro.entity.ProjectTask;
import com.backend.empowerpro.repository.ProjectTaskRepo;
import com.backend.empowerpro.service.ProjectTaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectTaskServiceImp implements ProjectTaskService {
    private  final ProjectTaskRepo projectTaskRepo;

    @Override
    public ProjectTask createProjectTask(ProjectTaskCreationDto projectTaskCreationDto) {
        ProjectTask projectTask = new ProjectTask();

        try {
            projectTask.setProject(projectTaskCreationDto.getProject());
            projectTask.setTaskTitle(projectTaskCreationDto.getTaskTitle());
            projectTask.setTaskDescription(projectTaskCreationDto.getTaskDescription());
            projectTask.setMembers(projectTaskCreationDto.getMembers());
            projectTask.setLabels(projectTaskCreationDto.getLabels());
            projectTask.setTaskStatus(projectTaskCreationDto.getTaskStatus());
            projectTask.setDueDate(projectTaskCreationDto.getDueDate());
            projectTask.setDueTime(projectTaskCreationDto.getDueTime());
            projectTask.setDateReminder(projectTaskCreationDto.getDateReminder());
            projectTask.setUrlLink(projectTaskCreationDto.getUrlLink());

            projectTaskRepo.save(projectTask);
            return projectTask;
        } catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred while creating projectTask", e);
        }

    }

    @Override
    public List<ProjectTask> getProjectTaskByProjectId(Long projectId) {
//        List<ProjectTask> projectTasks =projectTaskRepo.
        return null;
    }

    @Override
    public List<ProjectTask> getProjectTaskByUserId(Long userId) {
        return null;
    }

    @Override
    public List<ProjectTask> getProjectTaskById(Long projectTaskId) {
        return null;
    }

    @Override
    public String deleteProjectTask(Long projectTaskId) {
        return null;
    }

    @Override
    public ProjectTask updateProjectTask(Long projectTaskId) {
        return null;
    }
}
