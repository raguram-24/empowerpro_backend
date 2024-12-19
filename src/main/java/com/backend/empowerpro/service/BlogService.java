package com.backend.empowerpro.service;

import com.backend.empowerpro.dto.Blog.AddFavouriteDto;
import com.backend.empowerpro.dto.Blog.BlogCreationDto;
import com.backend.empowerpro.dto.Blog.BlogDto;
import com.backend.empowerpro.entity.AddFavouriteBlog;
import com.backend.empowerpro.entity.Blog;
import org.springframework.stereotype.Service;

import java.util.List;


public interface BlogService {

    Blog createBlog(BlogCreationDto blogCreationDto);

    List<Blog> getAllBlog();

    Blog getBlogById(Long BlogId) throws Throwable;

    String deleteBlog(Long blogId);

    List<Blog> searchBlogName(String keyword)throws Exception;

    List<Blog> getAllFavouriteBlog(Long userId);
}
