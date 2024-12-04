package com.backend.empowerpro.controller;

import com.backend.empowerpro.dto.remark.RemarkCreationDto;
import com.backend.empowerpro.dto.remark.RemarkDto;
import com.backend.empowerpro.entity.Remark;
import com.backend.empowerpro.service.RemarkService;
import com.backend.empowerpro.utils.RemarkMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/review")
@RequiredArgsConstructor

public class ReviewController {

    private final RemarkService remarkService;
    private final RemarkMapper remarkMapper;


    @PostMapping("/add-remark")
    public ResponseEntity<RemarkDto> addRemark(@RequestBody RemarkCreationDto remarkCreationDto) {
        RemarkDto createdRemark = remarkMapper.toRemarkDto(
                remarkService.addRemark(
                        remarkCreationDto.getReviewerActorId(),
                        remarkCreationDto.getReviewedActorId(),
                        remarkCreationDto.getContent()));


        return ResponseEntity.ok(createdRemark);
    }

    /**
     * Get all remarks for a specific reviewed actor.
     */
    @GetMapping("/remarks/{reviewerActorId}")
    public ResponseEntity<List<RemarkDto>> getRemarksByReviewedActor(@PathVariable Long reviewerActorId) {
        List<RemarkDto> remarks = remarkService.getRemarksByReviewerActor(reviewerActorId)
                .stream().map(Remark::toRemarkDto)
                .toList();

        return ResponseEntity.ok(remarks);
    }
}
