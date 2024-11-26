package com.backend.empowerpro.dto.remark;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RemarkCreationDto {
    private Long reviewerActorId;
    private Long reviewedActorId;
    private String content;
}