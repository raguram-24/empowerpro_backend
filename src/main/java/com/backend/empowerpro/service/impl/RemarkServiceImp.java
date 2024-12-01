package com.backend.empowerpro.service.impl;

import com.backend.empowerpro.entity.Remark;
import com.backend.empowerpro.auth.entity.Employee;
import com.backend.empowerpro.exception.EmployeeNotFoundException;
import com.backend.empowerpro.repository.RemarkRepo;
import com.backend.empowerpro.auth.repository.EmployeeRepository;
import com.backend.empowerpro.service.RemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class RemarkServiceImp implements RemarkService {

    private final RemarkRepo remarkRepo;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public RemarkServiceImp(RemarkRepo remarkRepo, EmployeeRepository employeeRepository) {
        this.remarkRepo = remarkRepo;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Remark> getRemarksByReviewedActor(Long reviewedActorId) {
        return remarkRepo.findByReviewedActorId(reviewedActorId);
    }

    @Override
    public List<Remark> getRemarksByReviewer(Long reviewerActorId) {
        return remarkRepo.findByReviewerActorId(reviewerActorId);
    }

    @Override
    @Transactional
    public Remark addRemark(Long reviewerActorId, Long reviewedActorId, String content) {
        Employee reviewer = employeeRepository.findById(reviewerActorId)
                .orElseThrow(() -> new EmployeeNotFoundException("Reviewer not found"));

        Employee reviewed = employeeRepository.findById(reviewedActorId)
                .orElseThrow(() -> new EmployeeNotFoundException("Reviewed actor not found"));

        Remark remark = new Remark();
        remark.setReviewerActor(reviewer);
        remark.setReviewedActor(reviewed);
        remark.setContent(content);
        remark.setDate(LocalDate.now());

        return remarkRepo.save(remark);
    }
}
