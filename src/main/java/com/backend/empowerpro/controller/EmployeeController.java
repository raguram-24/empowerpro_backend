package com.backend.empowerpro.controller;

import com.backend.empowerpro.auth.utils.EmployeeUpdateRequest;
import com.backend.empowerpro.dto.remark.RemarkCreationDto;
import com.backend.empowerpro.dto.remark.RemarkDto;
import com.backend.empowerpro.service.EmployeeService;
import com.backend.empowerpro.service.RemarkService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    private EmployeeUpdateRequest convertToEmployeeUpdateRequest(String employeeUpdateReq) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(employeeUpdateReq, EmployeeUpdateRequest.class);
    }


}
