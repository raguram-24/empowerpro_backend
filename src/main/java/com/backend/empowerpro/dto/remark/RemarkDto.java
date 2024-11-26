package com.backend.empowerpro.dto.remark;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RemarkDto {
    private Long id;
    private Long reviewedActorId;  // ID of the employee being reviewed
    private Long reviewerActorId;  // ID of the reviewer
    private String content;
    private LocalDate date;
}
