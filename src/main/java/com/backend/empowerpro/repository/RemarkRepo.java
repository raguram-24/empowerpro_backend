package com.backend.empowerpro.repository;

import com.backend.empowerpro.entity.Remark;
import com.backend.empowerpro.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RemarkRepo extends JpaRepository<Remark, Long> {

    // Find all remarks for a specific reviewed actor
    List<Remark> findByReviewedActor(Employee reviewedActor);

    // Find all remarks made by a specific reviewer
    List<Remark> findByReviewerActor(Employee reviewerActor);
}
