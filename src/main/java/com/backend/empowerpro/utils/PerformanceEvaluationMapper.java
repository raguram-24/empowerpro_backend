package com.backend.empowerpro.utils;

import com.backend.empowerpro.dto.performanceevaluation.PerformanceEvaluationCreationDto;
import com.backend.empowerpro.dto.performanceevaluation.PerformanceEvaluationDto;
import com.backend.empowerpro.entity.PerformanceEvaluation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PerformanceEvaluationMapper {
    PerformanceEvaluationDto toPerformanceEvaluationDto(PerformanceEvaluation performanceEvaluation);

    PerformanceEvaluation toPerformanceEvaluation(PerformanceEvaluationCreationDto performanceEvaluationCreationDto);
}
