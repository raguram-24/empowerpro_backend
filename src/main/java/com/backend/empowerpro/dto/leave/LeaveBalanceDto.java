package com.backend.empowerpro.dto.leave;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class LeaveBalanceDto {
    private int totalAvailableLeaves;
    private int approvedLeaves;
    private int rejectedLeaves;
}
