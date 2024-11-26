package com.backend.empowerpro.service;

import com.backend.empowerpro.entity.PerformanceEvaluation;
import java.util.List;

public interface PerformanceEvaluationService {

    /**
     * Enables or disables performance evaluation functionality for all actors.
     * @param enable True to enable, false to disable.
     */
    void togglePerformanceEvaluation(boolean enable);

    /**
     * Checks if performance evaluation is currently enabled.
     * @return True if enabled, false otherwise.
     */
    boolean isEvaluationEnabled(); // Add this method

    /**
     * Retrieves the performance evaluation for a specific actor.
     * @param actorId ID of the actor.
     * @return Performance evaluation for the actor.
     */
    PerformanceEvaluation getEvaluationByActorId(Long actorId);

    /**
     * Retrieves all performance evaluations.
     * @return List of all performance evaluations.
     */
    List<PerformanceEvaluation> getAllEvaluations();

    /**
     * Adds or updates a performance evaluation for a specific actor.
     * @param actorId -ID of the actor.
     * @param evaluationContent -Content of the evaluation.
     * @return The created or updated PerformanceEvaluation.
     */
    PerformanceEvaluation addOrUpdateEvaluation(Long actorId, String evaluationContent);
}
