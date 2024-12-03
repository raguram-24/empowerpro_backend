package com.backend.empowerpro.repository;

import com.backend.empowerpro.entity.PayRoll;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PayrollRepo extends JpaRepository<PayRoll,Long>{
        public  List<PayRoll> findByEmployeeId(Long id);
}
