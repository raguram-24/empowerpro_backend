package com.backend.empowerpro.repository;

import com.backend.empowerpro.entity.Tax;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaxRepo extends JpaRepository<Tax,Long> {
}
