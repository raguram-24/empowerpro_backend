package com.backend.empowerpro.service.impl;

import com.backend.empowerpro.entity.Blog;
import com.backend.empowerpro.entity.BlogView;
import com.backend.empowerpro.repository.BlogRepo;
import com.backend.empowerpro.repository.BlogViewRepo;
import com.backend.empowerpro.service.BlogViewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BlogViewServiceImpl implements BlogViewService {
    private final BlogViewRepo blogViewRepo;
    private final BlogRepo blogRepo;

    @Override
    public String createBlogView(Long userId, Blog blog){
        try {
            Optional<BlogView> existingBlogView = blogViewRepo.findByUserIdAndBlog(userId, blog);
            if (existingBlogView.isPresent()) {
                return "Blog view already exists for this user and blog.";
            }

            BlogView blogView = new BlogView();
            blogView.setUserId(userId);
            blogView.setBlog(blog);
            blogViewRepo.save(blogView);

            return "Successfully added blog view.";
        } catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred while creating BlogView", e);
        }
    }


    @Override
        public long getBlogViewCountForBlog(Long blogId) {
            Blog blog = blogRepo.findById(blogId)
                    .orElseThrow(() -> new RuntimeException("Blog not found"));
            return blogViewRepo.countByBlog(blog);
        }


}
