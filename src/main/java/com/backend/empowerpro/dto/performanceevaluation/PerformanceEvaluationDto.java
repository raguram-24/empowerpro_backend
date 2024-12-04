package com.backend.empowerpro.dto.performanceevaluation;

import com.backend.empowerpro.dto.remark.RemarkDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PerformanceEvaluationDto {
    private Long id;
    private Long actor;  // ID of the employee being evaluated
    private String finalFeedback;
    private LocalDate createdAt;
    private List<RemarkDto> remarks;  // List of associated remarks
}
