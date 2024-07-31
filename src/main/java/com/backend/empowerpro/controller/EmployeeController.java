package com.backend.empowerpro.controller;


import com.backend.empowerpro.dto.complaint.ComplaintDto;
import com.backend.empowerpro.dto.employee.EmployeeCreationDto;
import com.backend.empowerpro.entity.Employee;
import com.backend.empowerpro.service.ComplaintService;
import com.backend.empowerpro.service.impl.EmployeeServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/employees")
@RequiredArgsConstructor
public class EmployeeController {
//    public final ComplaintService complaintService;
    private final EmployeeServiceImp employeeServiceImp;
    @PostMapping("/creation")
    public ResponseEntity<Employee> creation(@RequestBody EmployeeCreationDto employeeCreationDto) {
        return ResponseEntity.ok(employeeServiceImp.createEmployee(employeeCreationDto));
    }

//    @PostMapping("/complaint-creation")
//    public ResponseEntity<String> creation(@RequestBody ComplaintDto complaintDto) {
//        return ResponseEntity.ok(complaintService.createComplaint(complaintDto));
//    }
//
//    @GetMapping("/complaint-FromMyself")
//    public ResponseEntity<List<ComplaintDto>> getComplaintFromMyself() {
//        return ResponseEntity.ok(complaintService.getComplaintsFromMyself());
//    }
}
