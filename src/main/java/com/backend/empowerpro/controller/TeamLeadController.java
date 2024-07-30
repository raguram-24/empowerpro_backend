package com.backend.empowerpro.controller;

public class TeamLeadController {

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
