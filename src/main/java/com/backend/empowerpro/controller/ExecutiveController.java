package com.backend.empowerpro.controller;

import com.backend.empowerpro.dto.performanceevaluation.PerformanceEvaluationCreationDto;
import com.backend.empowerpro.dto.performanceevaluation.PerformanceEvaluationDto;
import com.backend.empowerpro.dto.remark.RemarkCreationDto;
import com.backend.empowerpro.dto.remark.RemarkDto;
import com.backend.empowerpro.service.PerformanceEvaluationService;
import com.backend.empowerpro.service.RemarkService;
import com.backend.empowerpro.utils.PerformanceEvaluationMapper;
import com.backend.empowerpro.utils.RemarkMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/executive")
@RequiredArgsConstructor
public class ExecutiveController {

    private final PerformanceEvaluationService performanceEvaluationService;
    private final RemarkService remarkService;
    private final PerformanceEvaluationMapper performanceEvaluationMapper;
    private final RemarkMapper remarkMapper;

    /**
     * Enable or disable performance evaluation functionality.
     */
    @PatchMapping("/toggle-performance-evaluation")
    public ResponseEntity<String> togglePerformanceEvaluation(@RequestParam boolean enable) {
        performanceEvaluationService.togglePerformanceEvaluation(enable);
        String status = enable ? "enabled" : "disabled";
        return ResponseEntity.ok("Performance evaluation has been " + status + ".");
    }

    /**
     * Get all performance evaluations.
     */
    @GetMapping("/performance-evaluations")
    public ResponseEntity<List<PerformanceEvaluationDto>> getAllPerformanceEvaluations() {
        List<PerformanceEvaluationDto> evaluations = performanceEvaluationService.getAllEvaluations()
                .stream()
                .map(performanceEvaluationMapper::toPerformanceEvaluationDto)
                .toList();
        return ResponseEntity.ok(evaluations);
    }

    /**
     * Add or update a performance evaluation for an actor.
     */
    @PostMapping("/add-performance-evaluation")
    public ResponseEntity<PerformanceEvaluationDto> addOrUpdatePerformanceEvaluation(
            @RequestBody PerformanceEvaluationCreationDto evaluationDto) {
        PerformanceEvaluationDto updatedEvaluation = performanceEvaluationMapper.toPerformanceEvaluationDto(
                performanceEvaluationService.addOrUpdateEvaluation(
                        evaluationDto.getActorId(), evaluationDto.getEvaluationContent()));
        return ResponseEntity.ok(updatedEvaluation);
    }

    /**
     * Add a remark for another actor.
     */
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
    @GetMapping("/{reviewedActorId}/remarks")
    public ResponseEntity<List<RemarkDto>> getRemarksByReviewedActor(@PathVariable Long reviewedActorId) {
        List<RemarkDto> remarks = remarkService.getRemarksByReviewedActor(reviewedActorId)
                .stream()
                .map(remarkMapper::toRemarkDto)
                .toList();
        return ResponseEntity.ok(remarks);
    }
}
