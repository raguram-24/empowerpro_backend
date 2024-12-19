package com.backend.empowerpro.repository;

import com.backend.empowerpro.entity.AddFavouriteBlog;
import com.backend.empowerpro.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AddFavouriteRepo extends JpaRepository<AddFavouriteBlog, Long> {
    List<AddFavouriteBlog> findByUserId(Long userId);

//    AddFavouriteBlog findByBlog(Long userId ,Long blogId);
    @Query(value = "SELECT af.favourite_id FROM add_favourite af " +
            "JOIN blog b ON af.blog_id = b.blog_id " +
            "WHERE af.user_id = :userId AND b.blog_id = :blogId",
            nativeQuery = true)
    Long findByBlog(
            @Param("userId") Long userId,
            @Param("blogId") Long blogId);

}
