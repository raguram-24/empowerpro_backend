package com.backend.empowerpro.repository;


import com.backend.empowerpro.entity.Complaint;
import com.backend.empowerpro.entity.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ComplaintRepo extends JpaRepository<Complaint,Long> {
    List<Complaint> findByAssignedTo(String assignedTo);
//    @Query("SELECT c FROM Complaint c WHERE c.sender.id = :userId")
//    List<Complaint> findComplaintsBySenderId(@Param("userId") Long userId);
    List<Complaint> findBySender_Id(Long userId);
    @Query("SELECT c FROM Complaint c WHERE LOWER(c.about) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<Complaint> searchByAbout(@Param("searchTerm") String searchTerm);

}
