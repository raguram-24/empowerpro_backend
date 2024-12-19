package com.backend.empowerpro.dto.attendance;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class SearchDateRangeDto {
    private  Long userId;
    private LocalDate startDate;
    private LocalDate endDate;
}
