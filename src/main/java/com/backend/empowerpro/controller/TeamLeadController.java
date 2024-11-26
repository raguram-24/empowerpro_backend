package com.backend.empowerpro.controller;

import com.backend.empowerpro.dto.MarkCalendar.MarkCalendarDto;
import com.backend.empowerpro.dto.complaint.ComplaintDto;
import com.backend.empowerpro.service.ComplaintService;
import com.backend.empowerpro.service.MarkCalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/teamlead")
@RequiredArgsConstructor
@CrossOrigin("*")
public class TeamLeadController {
    private final MarkCalendarService markCalendarService;
//    public final ComplaintService complaintService;
//    @PostMapping("/complaint-creation")
//    public ResponseEntity<String> creation(@RequestBody ComplaintDto complaintDto) {
//        return ResponseEntity.ok(complaintService.createComplaint(complaintDto));
//    }
//    @GetMapping("/complaint-ToMe")
//    public ResponseEntity<List<ComplaintDto>> getComplaintsToMe() {
//        return ResponseEntity.ok(complaintService.getComplaintsToMe());
//    }
//
//    @GetMapping("/complaint-FromMyself")
//    public ResponseEntity<List<ComplaintDto>> getComplaintsFromMyself() {
//        return ResponseEntity.ok(complaintService.getComplaintsFromMyself());
//    }

//   -------------------------------------  MarkCalendar Part ---------------------------------
    @PostMapping("/markcalendar-create")
    public ResponseEntity<String> creation(@RequestBody MarkCalendarDto markCalendarDto){
        return ResponseEntity.ok(markCalendarService.createMarkCalendar(markCalendarDto));
    }
}
