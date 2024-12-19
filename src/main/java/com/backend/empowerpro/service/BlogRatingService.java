package com.backend.empowerpro.service;

import com.backend.empowerpro.entity.Blog;

public interface BlogRatingService {
    String createBlogRating(Long userId, Blog blog, int rate);

    float getBlogRating(Long blogId);
}
