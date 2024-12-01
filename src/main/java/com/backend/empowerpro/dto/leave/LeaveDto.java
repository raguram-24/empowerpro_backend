package com.backend.empowerpro.dto.leave;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.catalina.util.Introspection;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class LeaveDto {
    private Long id;
    private Long senderId;
    private String leaveType;
    private int leaveDays;
    private Date startDate;
    private Date endDate;
    private String reason;
    private String status;

}
