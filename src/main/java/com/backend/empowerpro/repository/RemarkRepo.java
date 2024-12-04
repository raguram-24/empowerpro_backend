package com.backend.empowerpro.repository;

import com.backend.empowerpro.entity.Complaint;
import com.backend.empowerpro.entity.Remark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RemarkRepo extends JpaRepository<Remark, Long> {

    //  to find remarks by reviewed actor ID
    List<Remark> findByReviewedActorId(Long reviewedActorId);

    //  to find remarks by reviewer actor ID
    @Query("SELECT r FROM Remark r WHERE r.reviewerActor.id = :reviewerActorId")
    List<Remark> findByReviewerActor_Id(@Param("reviewerActorId") Long reviewerActorId);

//    List<Remark> findByReviewerActor_Id(Long userId);

}
