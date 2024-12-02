package com.backend.empowerpro.repository;

import com.backend.empowerpro.entity.MedicalClaim;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalClaimRepo extends JpaRepository<MedicalClaim,Long> {
}
