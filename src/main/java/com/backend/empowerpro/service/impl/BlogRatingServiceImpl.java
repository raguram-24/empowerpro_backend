package com.backend.empowerpro.service.impl;

import com.backend.empowerpro.entity.Blog;
import com.backend.empowerpro.entity.BlogRating;
import com.backend.empowerpro.repository.BlogRatingRepo;
import com.backend.empowerpro.repository.BlogRepo;
import com.backend.empowerpro.service.BlogRatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BlogRatingServiceImpl implements BlogRatingService {
    private final BlogRatingRepo blogRatingRepo;

    @Override
    public String createBlogRating(Long userId, Blog blog, int rate){
        try {
            Optional<BlogRating> existingBlogRating = blogRatingRepo.findByUserIdAndBlog(userId, blog);
            if (existingBlogRating.isPresent()) {
                return "Blog rating already exists for this user and blog.";
            }

            BlogRating blogRating=new BlogRating();
            blogRating.setUserId(userId);
            blogRating.setBlog(blog);
            blogRating.setRate(rate);
            blogRatingRepo.save(blogRating);

            return "Successfully added blog Rating.";
        } catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred while creating BlogRating", e);
        }
    }

    @Override
    public float getBlogRating(Long blogId){
        float totalRating = blogRatingRepo.sumRatingsByBlogId(blogId);
        long ratingCount = blogRatingRepo.countRatingsByBlogId(blogId);
        if (ratingCount == 0) {
            return 0;
        }
        return totalRating / ratingCount;
    }

}
