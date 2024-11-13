package com.backend.empowerpro.repository;

import com.backend.empowerpro.auth.entity.Employee;
import com.backend.empowerpro.entity.Remark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RemarkRepo extends JpaRepository<Remark, Long> {
    List<Remark> findByReviewedActor(Employee reviewedActor);
    List<Remark> findByReviewerActor(Employee reviewerActor);
}
