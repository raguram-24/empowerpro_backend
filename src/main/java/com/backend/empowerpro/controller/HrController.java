package com.backend.empowerpro.controller;


<<<<<<< Updated upstream
import com.backend.empowerpro.dto.employee.EmployeeCreationDto;
import com.backend.empowerpro.dto.vacancy.VacancyCreationDto;
import com.backend.empowerpro.dto.vacancy.VacancyDto;
import com.backend.empowerpro.entity.Employee;
=======

import com.backend.empowerpro.dto.leave.LeaveCreationDto;
import com.backend.empowerpro.dto.leave.LeaveDto;
import com.backend.empowerpro.dto.vacancy.VacancyCreationDto;
import com.backend.empowerpro.dto.vacancy.VacancyDto;
import com.backend.empowerpro.service.LeaveService;
>>>>>>> Stashed changes
import com.backend.empowerpro.service.VacancyService;
import com.backend.empowerpro.service.impl.EmployeeServiceImp;
import com.backend.empowerpro.service.impl.VacancyServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/hr")
@RequiredArgsConstructor
public class HrController {
    private final VacancyService vacancyService;
    private final LeaveService leaveService;
    @PostMapping("/vacancy-creation")
    public ResponseEntity<String> creation(@RequestBody VacancyCreationDto vacancyCreationDto) {
        return ResponseEntity.ok(vacancyService.createVacancy(vacancyCreationDto));
    }
    @GetMapping("/vacancy-get-all")
    public ResponseEntity<List<VacancyDto>> getAllVacancies() {
        return ResponseEntity.ok(vacancyService.getAllVacancies());
    }

    @GetMapping("/leave-get-all")
    public ResponseEntity<List<LeaveDto>> getAllLeaves() {
        return ResponseEntity.ok(leaveService.getAllLeaves());
    }

    @GetMapping("/vacancy-get-one/{id}")
    public ResponseEntity<VacancyDto> getOneVacancy(@PathVariable Long id) {
        return ResponseEntity.ok(vacancyService.getOneVacancy(id));
    }

    @PutMapping("/vacancy-update/{id}")
    public ResponseEntity<VacancyDto> updateVacancy(@PathVariable Long id, @RequestBody VacancyCreationDto vacancyCreationDto) {
        return ResponseEntity.ok(vacancyService.updateVacancy(id,vacancyCreationDto ));
    }

    @DeleteMapping ("/vacancy-delete/{id}")
    public ResponseEntity<String> deleteVacancy(@PathVariable Long id) {
        return ResponseEntity.ok(vacancyService.deleteVacancy(id));
    }

<<<<<<< Updated upstream
      @PostMapping("/complaint-creation")
    public ResponseEntity<String> creation(@RequestBody ComplaintDto complaintDto) {
        return ResponseEntity.ok(complaintService.createComplaint(complaintDto));
    }
    @GetMapping("/complaint-ToMe")
    public ResponseEntity<List<ComplaintDto>> getComplaintsToMe() {
        return ResponseEntity.ok(complaintService.getComplaintsToMe());
    }

    @GetMapping("/complaint-FromMyself")
    public ResponseEntity<List<VacancyDto>> getComplaintsFromMyself() {
        return ResponseEntity.ok(vacancyService.getComplaintsFromMyself());
=======
    @PostMapping("/leave-creation")
    public ResponseEntity<String> leaveCreation(@RequestBody LeaveCreationDto leaveCreationDto) {
        return ResponseEntity.ok(leaveService.createLeave(leaveCreationDto));
>>>>>>> Stashed changes
    }


    @GetMapping("/leave-get-one/{id}")
    public ResponseEntity<LeaveDto> getOneLeave(@PathVariable Long id) {
        return ResponseEntity.ok(leaveService.getOneLeave(id));
    }

    @PutMapping("/leave-update/{id}")
    public ResponseEntity<LeaveDto> updateLeave(@PathVariable Long id, @RequestBody LeaveCreationDto leaveCreationDto) {
        return ResponseEntity.ok(leaveService.updateLeave(id,leaveCreationDto ));
    }

    @DeleteMapping ("/leave-delete/{id}")
    public ResponseEntity<String> deleteLeave(@PathVariable Long id) {
        return ResponseEntity.ok(leaveService.deleteLeave(id));
    }

}
