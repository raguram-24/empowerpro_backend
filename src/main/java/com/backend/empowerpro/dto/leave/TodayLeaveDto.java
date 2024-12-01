package com.backend.empowerpro.dto.leave;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class TodayLeaveDto {
    private Long employeeId;
    private String employeeName;
    private LocalDate endDate;
    private String role;
}
