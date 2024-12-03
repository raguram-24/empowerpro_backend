package com.backend.empowerpro.repository;

import com.backend.empowerpro.entity.Blog;
import com.backend.empowerpro.entity.BlogRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BlogRatingRepo extends JpaRepository<BlogRating,Long> {
    Optional<BlogRating> findByUserIdAndBlog(Long userId, Blog blog);

    @Query("SELECT COALESCE(SUM(br.rate), 0) FROM BlogRating br WHERE br.blog.blogId = :blogId")
    float sumRatingsByBlogId(@Param("blogId") Long blogId);

    @Query("SELECT COUNT(br) FROM BlogRating br WHERE br.blog.blogId = :blogId")
    long countRatingsByBlogId(@Param("blogId") Long blogId);
}
