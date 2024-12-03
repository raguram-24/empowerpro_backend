package com.backend.empowerpro.repository;

import com.backend.empowerpro.entity.Tax;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TaxRepo extends JpaRepository<Tax,Long> {

    @Query("SELECT e FROM Tax e WHERE e.id = (SELECT MAX(e2.id) FROM Tax e2)")
    Tax findLatestById();
}
