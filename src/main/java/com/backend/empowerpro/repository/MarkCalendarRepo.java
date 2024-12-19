package com.backend.empowerpro.repository;


import com.backend.empowerpro.entity.MarkCalendar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MarkCalendarRepo extends JpaRepository<MarkCalendar, Long> {

    List<MarkCalendar> findMarkCalendarById(Long userId);
}
