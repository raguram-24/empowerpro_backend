package com.backend.empowerpro.controller;


import com.backend.empowerpro.dto.employee.EmployeeCreationDto;
import com.backend.empowerpro.dto.vacancy.VacancyCreationDto;
import com.backend.empowerpro.dto.vacancy.VacancyDto;
import com.backend.empowerpro.entity.Employee;
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
    @PostMapping("/vacancy-creation")
    public ResponseEntity<String> creation(@RequestBody VacancyCreationDto vacancyCreationDto) {
        return ResponseEntity.ok(vacancyService.createVacancy(vacancyCreationDto));
    }
    @GetMapping("/vacancy-get-all")
    public ResponseEntity<List<VacancyDto>> getAllVacancies() {
        return ResponseEntity.ok(vacancyService.getAllVacancies());
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
    }




}
