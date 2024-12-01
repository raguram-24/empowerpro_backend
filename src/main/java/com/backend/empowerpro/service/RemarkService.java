package com.backend.empowerpro.service;

import com.backend.empowerpro.entity.Remark;
import java.util.List;

public interface RemarkService {

    /**
     * Retrieves all remarks for a specific reviewed actor.
     *
     * @param reviewedActorId ID of the actor being reviewed.
     * @return List of remarks for the reviewed actor.
     */
    List<Remark> getRemarksByReviewedActor(Long reviewedActorId);

    /**
     * Adds a remark for a reviewed actor by a reviewer.
     *
     * @param reviewerActorId ID of the actor giving the review.
     * @param reviewedActorId ID of the actor being reviewed.
     * @param content Content of the remark.
     * @return The created Remark.
     */
    Remark addRemark(Long reviewerActorId, Long reviewedActorId, String content);

    /**
     * Retrieves all remarks given by a specific reviewer.
     *
     * @param reviewerActorId ID of the reviewer actor.
     * @return List of remarks given by the reviewer.
     */
    List<Remark> getRemarksByReviewer(Long reviewerActorId);
}
