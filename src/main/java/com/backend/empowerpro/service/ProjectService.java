package com.backend.empowerpro.service;

import com.backend.empowerpro.dto.Project.ProjectCreationDto;
import com.backend.empowerpro.entity.Project;

import java.util.List;

public interface ProjectService {
    Project createProject(ProjectCreationDto projectCreationDto);
    Project getProjectById(Long projectId) ;
    List<Project> getAllProject();
    List<Project> getProjectByUserId(Long userId);

    List<Project> searchProjectByName(String keyword);

    String deleteProject(Long projectId);

    List<Project> getProjectByTeamLeader(Long teamLeaderId);
}
