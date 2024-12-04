package com.backend.empowerpro.service;

import com.backend.empowerpro.dto.payroll.PayRollDto;
import com.backend.empowerpro.dto.payroll.PayRollReq;
import com.backend.empowerpro.dto.payroll.PayRollViewDto;
import com.backend.empowerpro.entity.PayRoll;

import java.util.List;

public interface PayrollService {
    public PayRoll getPayroll(PayRollReq payRollReq);
    public List<PayRollViewDto> getAllPayRoll();
    public PayRoll getOnePayRoll(Long employeeId);
    public PayRoll updateStatus(Long id);
}
