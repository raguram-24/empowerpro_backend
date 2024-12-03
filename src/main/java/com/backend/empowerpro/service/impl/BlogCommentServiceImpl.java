package com.backend.empowerpro.service.impl;

import com.backend.empowerpro.dto.Blog.BlogCommentDto;
import com.backend.empowerpro.entity.BlogComment;
import com.backend.empowerpro.repository.BlogCommentRepo;
import com.backend.empowerpro.service.BlogCommentService;
import com.backend.empowerpro.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogCommentServiceImpl implements BlogCommentService {
    private final BlogCommentRepo blogCommentRepo;
    private final BlogService blogService;

    @Override
    public BlogComment createBlogComment(BlogCommentDto blogCommentDto){
        BlogComment blogComment = new BlogComment();
        try {
            blogComment.setUserId(blogCommentDto.getUserId());
            blogComment.setCommentText(blogCommentDto.getCommentText());
            blogComment.setBlog(blogService.getBlogById(blogCommentDto.getBlogId()));
            BlogComment savedBlogComment =blogCommentRepo.save(blogComment);
            return savedBlogComment;
        } catch (Throwable e) {
            throw new RuntimeException("An unexpected error occurred while creating comment", e);
        }
    }

    @Override
    public List<BlogComment> getAllCommentByBlogId(Long blogId) {
        return blogCommentRepo.findBlogCommentByBlogId(blogId);
    }

    @Override
    public String deleteCommentBlog(Long commentId){
        try {
            BlogComment existingComment = blogCommentRepo.findById(commentId).orElseThrow(() -> new RuntimeException("BlogComment not found with ID: " + commentId));
            blogCommentRepo.delete(existingComment);
            return "BlogComment deleted successfully with ID: " + commentId;
        } catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred while deleting BlogComment", e);
        }
    }
}
