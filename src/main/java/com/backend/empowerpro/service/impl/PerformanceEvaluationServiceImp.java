package com.backend.empowerpro.service.impl;

import com.backend.empowerpro.entity.PerformanceEvaluation;
import com.backend.empowerpro.auth.entity.Employee;
import com.backend.empowerpro.repository.PerformanceEvaluationRepo;
import com.backend.empowerpro.auth.repository.EmployeeRepository;
import com.backend.empowerpro.exception.EmployeeNotFoundException;
import com.backend.empowerpro.service.PerformanceEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
//
@Service
public class PerformanceEvaluationServiceImp implements PerformanceEvaluationService {

    private final PerformanceEvaluationRepo performanceEvaluationRepo;
    private final EmployeeRepository employeeRepository;
    private boolean evaluationEnabled = false; // Flag to track if evaluation is enabled

    @Autowired
    public PerformanceEvaluationServiceImp(PerformanceEvaluationRepo performanceEvaluationRepo, EmployeeRepository employeeRepository) {
        this.performanceEvaluationRepo = performanceEvaluationRepo;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void togglePerformanceEvaluation(boolean enable) {
        this.evaluationEnabled = enable;
    }

    @Override
    public boolean isEvaluationEnabled() {
        return evaluationEnabled;
    }

    @Override
    public PerformanceEvaluation getEvaluationByActorId(Long actorId) {
        return performanceEvaluationRepo.findByActorId(actorId)
                .orElseThrow(() -> new EmployeeNotFoundException("Evaluation for actor not found"));
    }

    @Override
    public List<PerformanceEvaluation> getAllEvaluations() {
        return performanceEvaluationRepo.findAll();
    }

    @Override
    public PerformanceEvaluation addOrUpdateEvaluation(Long actorId, String evaluationContent) {
        PerformanceEvaluation evaluation = performanceEvaluationRepo.findByActorId(actorId)
                .orElse(new PerformanceEvaluation());

        Employee actor = employeeRepository.findById(actorId)
                .orElseThrow(() -> new EmployeeNotFoundException("Actor not found"));

        evaluation.setActor(actor);
        evaluation.setFinalFeedback(evaluationContent);
        return performanceEvaluationRepo.save(evaluation);
    }
}
