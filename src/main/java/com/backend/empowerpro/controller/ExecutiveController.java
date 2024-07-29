package com.backend.empowerpro.controller;

public class ExecutiveController {
      @PostMapping("/complaint-creation")
    public ResponseEntity<String> creation(@RequestBody ComplaintDto complaintDto) {
        return ResponseEntity.ok(complaintService.createComplaint(complaintDto));
    }

    @GetMapping("/complaint-FromMyself")
    public ResponseEntity<List<VacancyDto>> getComplaintsFromMyself() {
        return ResponseEntity.ok(vacancyService.getComplaintsFromMyself());
    }
}
