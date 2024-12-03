package com.backend.empowerpro.service;

import com.backend.empowerpro.entity.Blog;

public interface BlogViewService {
    String createBlogView(Long userId, Blog blog);

    long getBlogViewCountForBlog(Long blogId);
}
