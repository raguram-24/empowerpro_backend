package com.backend.empowerpro.service.impl;

import com.backend.empowerpro.dto.Blog.BlogCreationDto;
import com.backend.empowerpro.entity.AddFavouriteBlog;
import com.backend.empowerpro.entity.Blog;
import com.backend.empowerpro.repository.BlogRepo;
import com.backend.empowerpro.service.AddFavouriteService;
import com.backend.empowerpro.service.BlogService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class BlogServiceImp implements BlogService {
    private final BlogRepo blogRepo;
    private final AddFavouriteService addFavouriteService;



    @Override
    public Blog createBlog(BlogCreationDto blogCreationDto) {
        Blog blog = new Blog();
        try{
            blog.setUserId(blogCreationDto.getId());
            blog.setTitle(blogCreationDto.getTitle());
            blog.setSearchNames(blogCreationDto.getSearchNames());
            blog.setContent(blogCreationDto.getContent());

            Blog createblog = blogRepo.save(blog);

            return  createblog;
        } catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred while creating blog", e);
        }
    }

    @Override
    public List<Blog> getAllBlog(){
        List<Blog> blogs = blogRepo.findAll();
        return blogs.stream().collect(Collectors.toList());

    }

    @Override
    public Blog getBlogById(Long blogId) throws Throwable {
        Blog blog= (Blog) blogRepo.findById(blogId)
                .orElseThrow( () ->
                        new RuntimeException("An unexpected error occurred while fetching blog"));
        return blog;
    }

    @Override
    public String deleteBlog(Long blogId){
        try {
            Blog existingBlog = blogRepo.findById(blogId).orElseThrow(() -> new RuntimeException("Blog not found with ID: " + blogId));
            blogRepo.delete(existingBlog);
            return "Blog deleted successfully with ID: " + blogId;
        } catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred while deleting Blog", e);
        }
    }

    @Override
    public List<Blog> searchBlogName(String keyword) {
        List<Blog> blogs = blogRepo.findBySearchNamesContainingIgnoreCase(keyword.trim());
        System.out.print(keyword);
        return blogs.stream().collect(Collectors.toList());
    }

    @Override
    public List<Blog> getAllFavouriteBlog(Long userId) {
        List<AddFavouriteBlog> addFavouriteBlogs = addFavouriteService.getAllFavouriteBlogsByUserId(userId);
        List<Long> favouriteBlogIds = addFavouriteBlogs.stream().map(AddFavouriteBlog::getBlogId).collect(Collectors.toList());

        List<Blog> blogs = blogRepo.findAll().stream().filter(blog -> favouriteBlogIds.contains(blog.getBlogId())).collect(Collectors.toList());
        return blogs.stream().collect(Collectors.toList());
    }



}


