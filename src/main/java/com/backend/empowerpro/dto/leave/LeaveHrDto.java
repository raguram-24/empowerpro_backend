package com.backend.empowerpro.dto.leave;

import jdk.jshell.Snippet;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LeaveHrDto {
    private Long id;
    private Long senderId;
    private String employeeName;
    private String leaveType;
    private int leaveDays;
    private Date startDate;
    private Date endDate;
    private String reason;
    private String status;

}
