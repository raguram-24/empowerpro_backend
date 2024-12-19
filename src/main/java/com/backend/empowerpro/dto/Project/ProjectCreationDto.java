package com.backend.empowerpro.dto.Project;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProjectCreationDto {
    private Long userId;
    private String projectName;
    private String projectDescription;
    private LocalDate startDate;
    private LocalDate endDate;
    private String clientName;
    private String type;
    private String stack;
    private Long teamlead;
    private List<Long> projectTeamMembers;
}
