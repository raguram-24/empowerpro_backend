package com.backend.empowerpro.repository;

import com.backend.empowerpro.entity.PerformanceEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PerformanceEvaluationRepo extends JpaRepository<PerformanceEvaluation, Long> {

    // to find a PerformanceEvaluation by actor ID
    Optional<PerformanceEvaluation> findByActorId(Long actorId);
}
