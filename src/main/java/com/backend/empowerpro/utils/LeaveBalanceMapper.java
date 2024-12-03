package com.backend.empowerpro.utils;

import com.backend.empowerpro.dto.leave.LeaveBalanceDto;
import com.backend.empowerpro.entity.Leave;
import com.backend.empowerpro.entity.LeaveBalance;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface LeaveBalanceMapper {
    LeaveBalanceDto toLeaveBalanceDto(LeaveBalance leaveBalance);
}

