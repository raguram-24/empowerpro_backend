package com.backend.empowerpro.repository;

import com.backend.empowerpro.entity.Project;
import com.backend.empowerpro.entity.ProjectTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectTaskRepo extends JpaRepository<ProjectTask,Long> {
    List<ProjectTask> findProjectTaskByProject(Project project);
}
