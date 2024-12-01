package com.backend.empowerpro.controller;

import com.backend.empowerpro.dto.remark.RemarkCreationDto;
import com.backend.empowerpro.dto.remark.RemarkDto;
import com.backend.empowerpro.service.RemarkService;
import com.backend.empowerpro.utils.RemarkMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/review")
@RequiredArgsConstructor
public class ReviewController {

    private final RemarkService remarkService;
    private final RemarkMapper remarkMapper;

    /**
     * Add a remark for another actor.
     *
     * @param reviewedActorId ID of the actor being reviewed.
     * @param remarkCreationDto Details of the remark.
     * @return Created RemarkDto.
     */
    @PostMapping("/{reviewedActorId}/add-remark")
    public ResponseEntity<RemarkDto> addRemark(
            @PathVariable Long reviewedActorId,
            @RequestBody RemarkCreationDto remarkCreationDto) {
        RemarkDto createdRemark = remarkMapper.toRemarkDto(
                remarkService.addRemark(
                        remarkCreationDto.getReviewerActorId(),
                        reviewedActorId,
                        remarkCreationDto.getContent()));
        return ResponseEntity.ok(createdRemark);
    }

    /**
     * Get all remarks received by a specific actor.
     *
     * @param reviewedActorId ID of the actor being reviewed.
     * @return List of received RemarkDto.
     */
    @GetMapping("/{reviewedActorId}/received-remarks")
    public ResponseEntity<List<RemarkDto>> getRemarksByReviewedActor(@PathVariable Long reviewedActorId) {
        List<RemarkDto> receivedRemarks = remarkService.getRemarksByReviewedActor(reviewedActorId)
                .stream()
                .map(remarkMapper::toRemarkDto)
                .toList();
        return ResponseEntity.ok(receivedRemarks);
    }

    /**
     * Get all remarks given by the current actor.
     *
     * @param reviewerActorId ID of the actor who gave the remarks.
     * @return List of given RemarkDto.
     */
    @GetMapping("/{reviewerActorId}/given-remarks")
    public ResponseEntity<List<RemarkDto>> getRemarksByReviewer(@PathVariable Long reviewerActorId) {
        List<RemarkDto> givenRemarks = remarkService.getRemarksByReviewer(reviewerActorId)
                .stream()
                .map(remarkMapper::toRemarkDto)
                .toList();
        return ResponseEntity.ok(givenRemarks);
    }
}
