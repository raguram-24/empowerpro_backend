package com.backend.empowerpro.repository;

import com.backend.empowerpro.dto.Project.ProjectTaskDisplayDto;
import com.backend.empowerpro.entity.Project;
import com.backend.empowerpro.entity.ProjectTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectTaskRepo extends JpaRepository<ProjectTask,Long> {
    List<ProjectTask> findProjectTaskByProjectId(Long projectId);

    List<ProjectTask> findProjectTaskByMembers(Long members);

    @Query("SELECT new com.backend.empowerpro.dto.Project.ProjectTaskDisplayDto(" +
            "pt.id, pt.projectId, p.projectName, pt.taskTitle, pt.taskDescription, pt.taskStatus, pt.members, pt.dueDate, pt.dueTime, pt.dateReminder) " +
            "FROM ProjectTask pt " +
            "JOIN Project p ON pt.projectId = p.id " +
            "WHERE pt.members = :employeeId")
    List<ProjectTaskDisplayDto> findProjectTasksWithProjectName(@Param("employeeId") Long employeeId);

    ProjectTask findProjectTaskById(Long id);

    @Query(value = "SELECT pt.id, pt.project_id, pt.task_title, pt.task_description, " +
            "pt.task_status, pt.members, pt.due_date, pt.due_time, pt.date_reminder " +
            "FROM project_task pt " +
            "JOIN project p ON pt.project_id = p.project_id " +
            "WHERE p.teamlead = :teamlead AND pt.task_status = 'check'",
            nativeQuery = true)
    List<ProjectTask> findCheckedTasksByTeamLead(@Param("teamlead") Long teamlead);

}
