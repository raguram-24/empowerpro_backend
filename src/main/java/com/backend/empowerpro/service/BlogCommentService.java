package com.backend.empowerpro.service;

import com.backend.empowerpro.dto.Blog.BlogCommentDto;
import com.backend.empowerpro.entity.BlogComment;

import java.util.List;

public interface BlogCommentService {
    BlogComment createBlogComment(BlogCommentDto blogCommentDto);
    List<BlogComment> getAllCommentByBlogId(Long blogId);

    String deleteCommentBlog(Long commentId);
}
