package com.backend.empowerpro.service.impl;

import com.backend.empowerpro.dto.Project.ProjectCreationDto;
import com.backend.empowerpro.entity.Project;
import com.backend.empowerpro.repository.ProjectRepo;
import com.backend.empowerpro.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepo projectRepo;

    @Override
    public Project createProject(ProjectCreationDto projectCreationDto){
        Project project = new Project();
        try{
            project.setUserId(projectCreationDto.getUserId());
            project.setProjectName(projectCreationDto.getProjectName());
            project.setProjectDescription(projectCreationDto.getProjectDescription());
            project.setStartDate(projectCreationDto.getStartDate());
            project.setEndDate(projectCreationDto.getEndDate());
            project.setClientName(projectCreationDto.getClientName());
            project.setType(projectCreationDto.getType());
            project.setStack(projectCreationDto.getStack());
            project.setTeamlead(projectCreationDto.getTeamlead());
            project.setProjectTeamMembers(projectCreationDto.getProjectTeamMembers());

            return projectRepo.save(project);
        } catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred while creating project", e);
        }
    }

    @Override
    public Project getProjectById(Long projectId) {
        Project project =(Project) projectRepo.getProjectByProjectId(projectId);
        return project;
    }

    @Override
    public List<Project> getAllProject() {
        List<Project> projects = projectRepo.findAll();
        return projects;
    }

    @Override
    public List<Project> getProjectByUserId(Long userId) {
        List<Project> projects = projectRepo.getProjectByUserId(userId);
        return projects;
    }

    @Override
    public List<Project> searchProjectByName(String keyword) {
        List<Project> projects = projectRepo.findProjectByProjectNameContaining(keyword.trim());
        return projects;
    }

    @Override
    public String deleteProject(Long projectId){
        try {
           Project existingProject = projectRepo.findById(projectId).orElseThrow(() -> new RuntimeException("Project not found with ID: " + projectId));
            projectRepo.delete(existingProject);
            return "Project deleted successfully with ID: " + projectId;
        } catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred while deleting Project", e);
        }
    }


}
