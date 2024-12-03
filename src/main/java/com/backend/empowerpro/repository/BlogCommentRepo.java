package com.backend.empowerpro.repository;

import com.backend.empowerpro.entity.AddFavouriteBlog;
import com.backend.empowerpro.entity.BlogComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BlogCommentRepo extends JpaRepository<BlogComment ,Long> {
    @Query(value = "SELECT * FROM blog_comment WHERE blog_id = :blogId", nativeQuery = true)
    List<BlogComment> findBlogCommentByBlogId(@Param("blogId") Long blogId);
}
