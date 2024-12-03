package com.backend.empowerpro.service.impl;

import com.backend.empowerpro.entity.AddFavouriteBlog;
import com.backend.empowerpro.entity.Blog;
import com.backend.empowerpro.repository.AddFavouriteRepo;
import com.backend.empowerpro.repository.BlogRepo;
import com.backend.empowerpro.service.AddFavouriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddFavouriteServiceImpl implements AddFavouriteService {
    private  final AddFavouriteRepo addFavouriteRepo;
    private final BlogRepo blogRepo;

    @Override
    public String createAddFavouriteBlog(Long userId, Blog blog) {
        AddFavouriteBlog addFavouriteBlog = new AddFavouriteBlog();

        try {
            addFavouriteBlog.setUserId(userId);
            addFavouriteBlog.setBlog(blog);
            addFavouriteRepo.save(addFavouriteBlog);
            return "Successfully addFavourite the blog";
        } catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred while creating addFavourite", e);
        }
    }

    @Override
    public String deleteAddFavouriteBlog(Long addFavouriteBlogId) {
        try {
            AddFavouriteBlog existingFavouriteBlog = addFavouriteRepo.findById(addFavouriteBlogId).orElseThrow(() -> new RuntimeException("FavouriteBlog not found with ID: " + addFavouriteBlogId));
            addFavouriteRepo.delete(existingFavouriteBlog);
            return "FavouriteBlog deleted successfully with ID: " + addFavouriteBlogId;
        } catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred while deleting FavouriteBlog", e);
        }
    }

    @Override
    public Long getFavouriteBlog(Long userId, Long blogId) throws Exception {
        Long favouriteId = addFavouriteRepo.findByBlog(userId, blogId);
        if (favouriteId == null) {
            throw new Exception("Favorite not found for user " + userId + " and blog " + blogId);
        }
        return favouriteId;
    }

    @Override
    public String deleteFavouriteBlog(Long userId, Long blogId) {

        try {
            Long addFavouriteBlogId = getFavouriteBlog(userId,blogId);
            AddFavouriteBlog existingFavouriteBlog = addFavouriteRepo.findById(addFavouriteBlogId).orElseThrow(() -> new RuntimeException("FavouriteBlog not found with ID: " + addFavouriteBlogId));
            addFavouriteRepo.delete(existingFavouriteBlog);
            return "FavouriteBlog deleted successfully with ID: " + addFavouriteBlogId;
        } catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred while deleting FavouriteBlog", e);
        }

    }

    @Override
    public List<AddFavouriteBlog> getAllFavouriteBlogsByUserId(Long userId) {
        return addFavouriteRepo.findByUserId(userId);
    }
}
