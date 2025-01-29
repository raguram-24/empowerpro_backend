package com.backend.empowerpro.service;

import com.backend.empowerpro.auth.entity.Employee;
import com.backend.empowerpro.dto.Project.ProjectTaskCreationDto;
import com.backend.empowerpro.dto.Project.ProjectTaskDisplayDto;
import com.backend.empowerpro.dto.employee.EmployeeDto;
import com.backend.empowerpro.entity.ProjectTask;

import java.util.List;

public interface ProjectTaskService {
    ProjectTask createProjectTask(ProjectTaskCreationDto projectTaskCreationDto);

    List<ProjectTask> getProjectTaskByProjectId(Long projectId);

    List<ProjectTaskDisplayDto> getProjectTaskByEmployeeId(Long employeeId);

    String updateProjectTask(Long id,String status);

    List<ProjectTask> findCheckedTasksByTeamLead(Long teamLeadId);

//    List<ProjectTask> getProjectTaskByUserId(Long userId);
//
//    List<ProjectTask> getProjectTaskById(Long projectTaskId);
//
//    String deleteProjectTask(Long projectTaskId);
//
//    ProjectTask updateProjectTask(Long projectTaskId);

    List<EmployeeDto> getEmployeeByProjectId(Long projectId);

//    List<ProjectTask> get
}
