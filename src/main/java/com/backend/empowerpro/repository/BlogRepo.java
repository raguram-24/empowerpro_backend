package com.backend.empowerpro.repository;
import com.backend.empowerpro.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepo extends JpaRepository<Blog, Long> {
//     List<Blog> findBySearchNamesContainingIgnoreCase( String title);

        @Query("SELECT b FROM Blog b JOIN b.searchNames s WHERE LOWER(s) LIKE LOWER(CONCAT('%', :keyword, '%'))")
        List<Blog> findBySearchNamesContainingIgnoreCase(@Param("keyword") String keyword);

}
