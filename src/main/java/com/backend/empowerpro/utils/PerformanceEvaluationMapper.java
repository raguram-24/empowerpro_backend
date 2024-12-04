package com.backend.empowerpro.utils;

import com.backend.empowerpro.auth.entity.Employee;
import com.backend.empowerpro.dto.performanceevaluation.PerformanceEvaluationCreationDto;
import com.backend.empowerpro.dto.performanceevaluation.PerformanceEvaluationDto;
import com.backend.empowerpro.entity.PerformanceEvaluation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PerformanceEvaluationMapper {
    default Long map(Employee employee) {
        return employee != null ? employee.getId() : null;
    }

    PerformanceEvaluationDto toPerformanceEvaluationDto(PerformanceEvaluation performanceEvaluation);

    PerformanceEvaluation toPerformanceEvaluation(PerformanceEvaluationCreationDto performanceEvaluationCreationDto);
}
