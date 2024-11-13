package com.backend.empowerpro.repository;

import com.backend.empowerpro.entity.PerformanceEvaluation;
import com.backend.empowerpro.auth.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PerformanceEvaluationRepo extends JpaRepository<PerformanceEvaluation, Long> {
    Optional<PerformanceEvaluation> findByActor(Employee actor);
}
