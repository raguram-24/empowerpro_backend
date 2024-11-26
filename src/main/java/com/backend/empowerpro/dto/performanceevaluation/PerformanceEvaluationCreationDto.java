package com.backend.empowerpro.dto.performanceevaluation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PerformanceEvaluationCreationDto {
    private Long actorId;
    private String evaluationContent;
}
