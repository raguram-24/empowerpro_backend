package com.backend.empowerpro.service.impl;

import com.backend.empowerpro.auth.entity.Employee;
import com.backend.empowerpro.auth.repository.EmployeeRepository;
import com.backend.empowerpro.dto.Project.ProjectTaskCreationDto;
import com.backend.empowerpro.dto.Project.ProjectTaskDisplayDto;
import com.backend.empowerpro.dto.employee.EmployeeDto;
import com.backend.empowerpro.entity.AddFavouriteBlog;
import com.backend.empowerpro.entity.Project;
import com.backend.empowerpro.entity.ProjectTask;
import com.backend.empowerpro.repository.ProjectRepo;
import com.backend.empowerpro.repository.ProjectTaskRepo;
import com.backend.empowerpro.service.EmployeeService;
import com.backend.empowerpro.service.ProjectService;
import com.backend.empowerpro.service.ProjectTaskService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectTaskServiceImp implements ProjectTaskService {
    private  final ProjectTaskRepo projectTaskRepo;
    private final ProjectService projectService;
    private final EmployeeService employeeService;
    private final EmployeeRepository employeeRepository;
    private  final ProjectRepo projectRepo;

    @Override
    public ProjectTask createProjectTask(ProjectTaskCreationDto projectTaskCreationDto) {
        ProjectTask projectTask = new ProjectTask();

        try {
            projectTask.setProjectId(projectTaskCreationDto.getProjectId());
            projectTask.setTaskTitle(projectTaskCreationDto.getTaskTitle());
            projectTask.setTaskDescription(projectTaskCreationDto.getTaskDescription());
            projectTask.setMembers(projectTaskCreationDto.getMembers());

            projectTask.setTaskStatus(projectTaskCreationDto.getTaskStatus());
            projectTask.setDueDate(projectTaskCreationDto.getDueDate());
            projectTask.setDueTime(projectTaskCreationDto.getDueTime());
            projectTask.setDateReminder(projectTaskCreationDto.getDateReminder());


            projectTaskRepo.save(projectTask);
            return projectTask;
        } catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred while creating projectTask", e);
        }

    }

    @Override
    public List<ProjectTask> getProjectTaskByProjectId(Long projectId) {
        List<ProjectTask> projectTasks =projectTaskRepo.findProjectTaskByProjectId(projectId);
        return null;
    }

    public List<ProjectTaskDisplayDto> getProjectTaskByEmployeeId(Long employeeId) {
        return projectTaskRepo.findProjectTasksWithProjectName(employeeId);
    }

    @Override
    public String updateProjectTask(Long id, String status) {
        ProjectTask projectTask = projectTaskRepo.findProjectTaskById(id);
        if (projectTask == null) {
            throw new IllegalArgumentException("No ProjectTask found with id: " + id);
        }
        projectTask.setTaskStatus(status);
        projectTaskRepo.save(projectTask);
        return "Updated ProjectTask";
    }

    @Override
    public List<ProjectTask> findCheckedTasksByTeamLead(Long teamLeadId) {
        return projectTaskRepo.findCheckedTasksByTeamLead(teamLeadId);
    }


    @Override
    public List<EmployeeDto> getEmployeeByProjectId(Long projectId) {
        Project project = projectService.getProjectById(projectId);
        List<Long> memberIds = project.getProjectTeamMembers();

        List<EmployeeDto> employees = new ArrayList<>();
        for (Long id : memberIds) {
            EmployeeDto employee = employeeService.getEmployeeById(id);
            if (employee != null) {
                employees.add(employee);
            }
        }

        return employees;
    }
//
//    @Override
//    public List<ProjectTask> getProjectTaskByUserId(Long userId) {
//        return null;
//    }
//
//    @Override
//    public List<ProjectTask> getProjectTaskById(Long projectTaskId) {
//        return null;
//    }
//
//    @Override
//    public String deleteProjectTask(Long projectTaskId) {
//        return null;
//    }
//
//    @Override
//    public ProjectTask updateProjectTask(Long projectTaskId) {
//        return null;
//    }
}
