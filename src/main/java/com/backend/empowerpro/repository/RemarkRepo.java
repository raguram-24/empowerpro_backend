package com.backend.empowerpro.repository;

import com.backend.empowerpro.entity.Remark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RemarkRepo extends JpaRepository<Remark, Long> {

    //  to find remarks by reviewed actor ID
    List<Remark> findByReviewedActorId(Long reviewedActorId);

    //  to find remarks by reviewer actor ID
    List<Remark> findByReviewerActorId(Long reviewerActorId);
}
