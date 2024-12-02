package com.backend.empowerpro.repository;

import com.backend.empowerpro.entity.BankDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepo extends JpaRepository<BankDetails,Long> {
}
