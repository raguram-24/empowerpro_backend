package com.backend.empowerpro.repository;

import com.backend.empowerpro.entity.Blog;
import com.backend.empowerpro.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectRepo extends JpaRepository<Project,Long> {
    Project getProjectByProjectId(Long projectId);

    List<Project> getProjectByUserId(Long UserId);

    List<Project> findProjectByProjectNameContaining( String keyword);

    List<Project> findProjectByTeamlead(long teamleadId);
}
