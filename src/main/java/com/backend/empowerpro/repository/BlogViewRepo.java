package com.backend.empowerpro.repository;

import com.backend.empowerpro.entity.Blog;
import com.backend.empowerpro.entity.BlogView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BlogViewRepo extends JpaRepository<BlogView,Long> {
    Optional<BlogView> findByUserIdAndBlog(Long userId, Blog blog);

    long countByBlog(Blog blog);
}
